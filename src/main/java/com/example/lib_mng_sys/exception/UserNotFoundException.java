package com.example.lib_mng_sys.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundException extends  RuntimeException {

    private String message;
}
