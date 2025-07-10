package com.example.lib_mng_sys.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseStructure<T> create(HttpStatus status, String message, T data){
        ResponseStructure<T> response=new ResponseStructure<>();
        response.status=status.value();
        response.message=message;
        response.data=data;

        return response;
    }


}
