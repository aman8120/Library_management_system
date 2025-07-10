package com.example.lib_mng_sys.mapper;

import com.example.lib_mng_sys.dto.request.RegistrationRequest;
import com.example.lib_mng_sys.dto.request.UserRequest;
import com.example.lib_mng_sys.dto.response.UserResponse;
import com.example.lib_mng_sys.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserResponse mapToUserResponse(User user);


    public void mapToNewUser(RegistrationRequest registrationRequest, @MappingTarget User child);


    public void mapToNewUser(UserRequest source, @MappingTarget User target);

    public List<UserResponse> toUserResponse(List<User> user);
}
