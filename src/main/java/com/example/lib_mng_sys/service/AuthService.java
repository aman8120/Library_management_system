package com.example.lib_mng_sys.service;


import com.example.lib_mng_sys.dto.request.Auth;
import com.example.lib_mng_sys.dto.request.LoginRequest;
import org.springframework.http.HttpHeaders;

public interface AuthService {


    Auth login(LoginRequest loginRequest);

    HttpHeaders logout(String accessToken, String refreshToken);
}
