package com.example.lib_mng_sys.controller;


import com.example.lib_mng_sys.dto.request.Auth;
import com.example.lib_mng_sys.dto.request.LoginRequest;
import com.example.lib_mng_sys.security.jwt.TokenBlackListService;
import com.example.lib_mng_sys.service.AuthService;
import com.example.lib_mng_sys.service.impl.TokenGenerationService;
import com.example.lib_mng_sys.util.ResponseBuilder;
import com.example.lib_mng_sys.util.ResponseStructure;
import com.example.lib_mng_sys.util.SimpleResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000", // ✅ Your frontend
        allowCredentials = "true"
)
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    private final TokenGenerationService tokenGenerationService;
    private final TokenBlackListService tokenBlackListService;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<Auth>> login(@RequestBody LoginRequest loginRequest) {
        Auth auth = authService.login(loginRequest);
        HttpHeaders httpHeaders = tokenGenerationService.grantAccessAndRefreshTokens(auth);
        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "Login Successful", auth);
    }

    @PostMapping("/logout")
    public ResponseEntity<SimpleResponseStructure> logout(
            @CookieValue("at") String accessToken,
            @CookieValue("rt") String refreshToken) {


        HttpHeaders headers = authService.logout(accessToken, refreshToken);


        return ResponseBuilder.ok("Logout Successfully", headers);

    }
}
