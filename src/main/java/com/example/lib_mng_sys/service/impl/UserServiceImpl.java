package com.example.lib_mng_sys.service.impl;

import com.example.lib_mng_sys.dto.request.RegistrationRequest;
import com.example.lib_mng_sys.dto.request.UserRequest;
import com.example.lib_mng_sys.dto.response.UserResponse;
import com.example.lib_mng_sys.enums.UserRole;
import com.example.lib_mng_sys.exception.UserNotFoundException;
import com.example.lib_mng_sys.mapper.UserMapper;
import com.example.lib_mng_sys.model.Admin;
import com.example.lib_mng_sys.model.NormalUser;
import com.example.lib_mng_sys.model.User;
import com.example.lib_mng_sys.repository.UserRepositoy;
import com.example.lib_mng_sys.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepositoy userRepositoy;
    private final UserMapper userMapper;

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User child = this.createUserByRole(registrationRequest.getRole());

        userMapper.mapToNewUser(registrationRequest, child);
        userRepositoy.save(child);
        return userMapper.mapToUserResponse(child);
    }


    @Override
    public List<UserResponse> findAllUser() {
        List<User> users = userRepositoy.findAll();
        System.out.println(users);
        return userMapper.toUserResponse(users);
    }


    @Override
    public UserResponse findUserByEmail(String email) {
        User user = userRepositoy.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserByEmail(String email, UserRequest userRequest) {
        Optional<User> optionalUser = userRepositoy.findByEmail(email);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();


            userMapper.mapToNewUser(userRequest, existingUser);

            // Save updated user
            userRepositoy.save(existingUser);

            return userMapper.mapToUserResponse(existingUser);
        }
        return null;
    }

    @Override
    public UserResponse findUserById(long id) {
        User user = userRepositoy.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + id));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(long id, UserRequest userRequest) {
        User exUser = userRepositoy.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found By Id"));

        userMapper.mapToNewUser(userRequest, exUser);

        User updated = userRepositoy.save(exUser);

        return userMapper.mapToUserResponse(updated);

    }

    @Override
    public UserResponse deleteUserById(long id) {
        User user = userRepositoy.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found By Id"));
        userRepositoy.delete(user);

        return userMapper.mapToUserResponse(user);

    }


    private User createUserByRole(UserRole role) {
        User user;
        switch (role) {
            case ADMIN -> user = new Admin();
            case NORMAL_USER -> user = new NormalUser();
            default -> throw new RuntimeException("Failed registration user,invalid User");
        }
        return user;
    }
}
