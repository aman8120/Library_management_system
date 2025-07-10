package com.example.lib_mng_sys.dto.request;


import com.example.lib_mng_sys.enums.UserRole;

public record Auth(Long userId,
                   String username,
                   String email,
                   UserRole role,
                   Long accessExpiration,
                   Long refreshExpiration) {
}
