package com.example.lib_mng_sys.exception;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException(String message) {

        super(message);
    }
}
