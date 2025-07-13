package com.example.lib_mng_sys.service.helper;


import com.example.lib_mng_sys.config.AppEnv;
import com.example.lib_mng_sys.security.jwt.JWTService;
import com.example.lib_mng_sys.security.jwt.TokenPayload;
import com.example.lib_mng_sys.security.jwt.TokenType;
import com.example.lib_mng_sys.security.util.CookieManger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
@AllArgsConstructor
public class TokenGenerationServiceHelper {

    private final JWTService jwtService;
    private final AppEnv env;
    private final CookieManger cookieManger;

    public String generateToken(TokenType tokenType, Map<String,Object> claims, Instant shouldExpireAt){
        TokenPayload payload = this.generateTokenPayload(tokenType, claims, shouldExpireAt);
        String token=jwtService.generateToken(payload);
        return cookieManger.generateCookie(tokenType.type(),token, Duration.between(Instant.now(),shouldExpireAt).getSeconds());
    }

    private TokenPayload generateTokenPayload(TokenType tokenType, Map<String,Object> claims, Instant shouldExpireAt ){
        Instant issuedAt = calculateIssuedAt(tokenType, shouldExpireAt);
        return new TokenPayload(claims, issuedAt, shouldExpireAt);

    }

    private Instant calculateIssuedAt(TokenType tokenType, Instant shouldExpireAt) {
        Instant issueAt;
        switch(tokenType){
            case ACCESS -> issueAt= shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getAccessValidity());
            case REFRESH -> issueAt= shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getRefreshValidity());
            default -> throw new IllegalArgumentException("Invalid TokenType passed as argument.");
        }
        return issueAt;
    }

}
