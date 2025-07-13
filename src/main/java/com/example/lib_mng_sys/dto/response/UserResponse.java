package com.example.lib_mng_sys.dto.response;

import com.example.lib_mng_sys.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponse {

    private long userId;

    private String userName;

    private String email;

    private String phNo;

    private UserRole role;


    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
