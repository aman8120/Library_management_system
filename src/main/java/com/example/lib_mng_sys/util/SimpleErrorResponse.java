package com.example.lib_mng_sys.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SimpleErrorResponse {
    private String type;
    private int status;  //404
    private String message;
}
