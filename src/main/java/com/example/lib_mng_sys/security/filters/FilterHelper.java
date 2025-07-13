package com.example.lib_mng_sys.security.filters;


import com.example.lib_mng_sys.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;


public class FilterHelper {

    public static String getToken(TokenType tokenType, Cookie[] cookies) {
        if (cookies == null) {
            return null; // Prevent NullPointerException if no cookies are present
        }

        String token = null;
        for (Cookie cookie : cookies) {
            if (tokenType.type().equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
