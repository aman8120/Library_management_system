package com.example.lib_mng_sys.service.impl;


import com.example.lib_mng_sys.dto.request.Auth;
import com.example.lib_mng_sys.dto.request.LoginRequest;
import com.example.lib_mng_sys.model.User;
import com.example.lib_mng_sys.repository.UserRepositoy;
import com.example.lib_mng_sys.security.jwt.TokenBlackListService;
import com.example.lib_mng_sys.security.jwt.TokenType;
import com.example.lib_mng_sys.security.util.CookieManger;
import com.example.lib_mng_sys.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepositoy userRepository;
    private final TokenBlackListService tokenBlackListService;
    private final CookieManger cookieManger;

    @Override
    public Auth login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);
        Optional<User> user;
        if (authentication.isAuthenticated()) {
            user = userRepository.findByEmail(loginRequest.email());
            return generateAuth(user);

        } else
            throw new UsernameNotFoundException("Failed to Authenticate.");

    }

    @Override
    public HttpHeaders logout(String accessToken, String refreshToken) {

        tokenBlackListService.blackListToken(accessToken);
        tokenBlackListService.blackListToken(refreshToken);

        String at = cookieManger.generateCookie(TokenType.ACCESS.type(), "", 0L);
        String rt = cookieManger.generateCookie(TokenType.REFRESH.type(), "", 0L);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(at, rt);
        return httpHeaders;
    }

    private static Auth generateAuth(Optional<User> user) {
        Instant now = Instant.now();
        Long accessExpiration = now.plusSeconds(3600).toEpochMilli();
        Long refreshExpiration = now.plusSeconds(129600).toEpochMilli();
        Auth auth = new Auth(user.get().getUserId(),
                user.get().getUserName(),
                user.get().getEmail(),
                user.get().getRole(),
                accessExpiration,
                refreshExpiration);
        return auth;
    }
}
