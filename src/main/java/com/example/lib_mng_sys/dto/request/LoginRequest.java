package com.example.lib_mng_sys.dto.request;


import com.example.lib_mng_sys.dto.rules.Email;
import com.example.lib_mng_sys.dto.rules.Password;

public record LoginRequest(@Email String email,
                           @Password String password) {
}
