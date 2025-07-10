package com.example.lib_mng_sys.util;

import com.example.lib_mng_sys.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> userNotFound(UserNotFoundException ex) {

        ErrorStructure<String> errorStructure = new ErrorStructure<String>();
        errorStructure.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorStructure.setErrorMessage(ex.getMessage());
        errorStructure.setError("USer is not  found!!");

        return new ResponseEntity<ErrorStructure<String>>(errorStructure, HttpStatus.NOT_FOUND);
    }
}
