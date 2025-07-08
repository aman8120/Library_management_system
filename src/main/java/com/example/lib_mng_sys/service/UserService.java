package com.example.lib_mng_sys.service;


import com.example.lib_mng_sys.dto.request.RegistrationRequest;
import com.example.lib_mng_sys.dto.request.UserRequest;
import com.example.lib_mng_sys.dto.response.UserResponse;
import com.example.lib_mng_sys.model.User;

import java.util.List;

public interface UserService {

    public UserResponse registerUser(RegistrationRequest registrationRequest);

    public List<UserResponse> findAllUser();

    public UserResponse findUserByEmail(String email);

    public UserResponse updateUserByEmail(String email, UserRequest userRequest);

    public UserResponse findUserById(long id);

    public UserResponse updateUserById(long id, UserRequest userRequest);

    public UserResponse deleteUserById(long id);
}
