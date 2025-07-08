package com.example.lib_mng_sys.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponseStructure {

    private int status;
    private String message;


    public static SimpleResponseStructure create(HttpStatus status, String message) {
        SimpleResponseStructure response = new SimpleResponseStructure();
        response.status = status.value();
        response.message = message;

        return response;
    }
}
