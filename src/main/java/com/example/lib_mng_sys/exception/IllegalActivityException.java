package com.example.lib_mng_sys.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IllegalActivityException extends RuntimeException {
    private final String message;
}
