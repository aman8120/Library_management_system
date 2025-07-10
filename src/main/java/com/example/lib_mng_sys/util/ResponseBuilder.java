package com.example.lib_mng_sys.util;

import com.example.lib_mng_sys.util.SimpleErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);

    }


    public static <T> ResponseEntity<ResponseStructure<T>> ok(String message, T data) {
        return success(HttpStatus.OK, message, data);
    }


    public static <T> ResponseEntity<ResponseStructure<T>> created(String message, T data) {
        return success(HttpStatus.CREATED, message, data);
    }


    public static <T> ResponseEntity<ResponseStructure<T>> found(String message, T data) {
        return success(HttpStatus.FOUND, message, data);
    }


    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, HttpHeaders headers, String message, T data) {
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .headers(headers)
                .body(structure);
    }

    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String message) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .message(message)
                .build();

        return ResponseEntity.status(status)
                .body(error);

    }


    /**
     * Helps to provide status code(created) for general operations using success method
     * Acts as factory method for success method
     *
     * @param message success message
     * @Returns ResponseEntity of type ResponseStructure of type SimpleErrorResponse
     */
    public static ResponseEntity<com.example.lib_mng_sys.util.SimpleErrorResponse> notFound(String message) {
        return error(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseEntity<com.example.lib_mng_sys.util.SimpleErrorResponse> forbidden(String message) {
        return error(HttpStatus.FORBIDDEN, message);
    }


    public static ResponseEntity<SimpleResponseStructure> ok(String message, HttpHeaders data) {
        SimpleResponseStructure sResponse = SimpleResponseStructure.builder()
                .message(message)
                .status(data.size())
                .build();
        return ResponseEntity.ok().body(sResponse);
    }
}
