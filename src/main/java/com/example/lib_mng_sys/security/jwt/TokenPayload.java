package com.example.lib_mng_sys.security.jwt;

import java.time.Instant;
import java.util.Map;

public record TokenPayload(Map<String,Object> claims,
                           Instant issuedAt,
                           Instant expiration
                           ) {
}
