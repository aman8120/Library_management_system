package com.example.lib_mng_sys.controller;

import com.example.lib_mng_sys.dto.request.RegistrationRequest;
import com.example.lib_mng_sys.dto.request.UserRequest;
import com.example.lib_mng_sys.dto.response.UserResponse;
import com.example.lib_mng_sys.model.User;
import com.example.lib_mng_sys.service.UserService;
import com.example.lib_mng_sys.util.ResponseBuilder;
import com.example.lib_mng_sys.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000", // ✅ Your frontend
        allowCredentials = "true"
)
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Validated RegistrationRequest registrationRequest) {
        UserResponse userResponse = userService.registerUser(registrationRequest);
        return ResponseBuilder.created("User Created", userResponse);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
        List<UserResponse> userResponse = userService.findAllUser();
        return ResponseBuilder.ok("User Found", userResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(@PathVariable String email) {

        UserResponse userResponse = userService.findUserByEmail(email);
        return ResponseBuilder.ok("User Found", userResponse);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long id) {

        UserResponse userResponse = userService.findUserById(id);
        return ResponseBuilder.ok("User Found", userResponse);

    }


    @PutMapping("/update/email/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserByEmail(@PathVariable String email, @RequestBody UserRequest userRequest) {

        UserResponse response = userService.updateUserByEmail(email, userRequest);
        return ResponseBuilder.ok("User Update", response);


    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable long id) {
        UserResponse userResponse = userService.deleteUserById(id);
        return ResponseBuilder.ok("User Deleted", userResponse);
    }


    @PutMapping("/update/id/{id}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long id, @RequestBody UserRequest userRequest) {

        UserResponse response = userService.updateUserById(id, userRequest);
        return ResponseBuilder.ok("User Update", response);


    }


}
