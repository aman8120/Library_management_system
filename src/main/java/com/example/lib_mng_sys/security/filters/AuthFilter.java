package com.example.lib_mng_sys.security.filters;


import com.example.lib_mng_sys.security.jwt.ClaimName;
import com.example.lib_mng_sys.security.jwt.JWTService;
import com.example.lib_mng_sys.security.jwt.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {


    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Validating request, finding token : {}", TokenType.ACCESS.type());
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            log.warn("No cookies found in the request");
        }

        String token = FilterHelper.getToken(TokenType.ACCESS, cookies);
        if (token != null) {
            log.info("Token found with name:{}", TokenType.ACCESS.type());
            String email = jwtService.parseToken(token).get(ClaimName.USER_EMAIL, String.class);
            String userRole = jwtService.parseToken(token).get(ClaimName.USER_ROLE, String.class);

            if ((email != null && !email.isBlank()) && (userRole != null && userRole != "")) {
                log.info("Claims:{} & {} extracted successfully", ClaimName.USER_EMAIL, ClaimName.USER_ROLE);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                            UsernamePasswordAuthenticationToken
                            (email, null, List.of(new SimpleGrantedAuthority("ROLE_" + userRole)));
                    usernamePasswordAuthenticationToken.setDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    log.info("Request Authentication successful");
                }
            } else
                log.error("Claims:{} & {} not found", ClaimName.USER_EMAIL, ClaimName.USER_ROLE);
        } else
            log.warn("Token not found with name :{}", TokenType.ACCESS.type());

        filterChain.doFilter(request, response);
    }

}
