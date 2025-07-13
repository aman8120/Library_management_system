package com.example.lib_mng_sys.security.jwt;

public enum TokenType {
    ACCESS("at"),
    REFRESH("rt");

    private final String type;

    TokenType(String type){

        this.type=type;
    }

    public String type() {

        return this.type;
    }
}
