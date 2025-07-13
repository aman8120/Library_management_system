package com.example.lib_mng_sys.security.util;

import com.example.lib_mng_sys.config.AppEnv;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CookieManger {

    private final AppEnv env;

    public String generateCookie(String name, String value, Long maxAge){

        return ResponseCookie.from(name, value)
                .domain(env.getDomain().getName())
                .path("/")
                .sameSite(env.getDomain().getSameSite())
                .httpOnly(true)
                .secure(env.getDomain().isSecure())
                .maxAge(maxAge).build().toString();
    }

}
