package com.github.ungoodman.springwebfluxtest.model.enums;

public enum StatusCode {
    ACTIVE("A"),
    DELETED("D"),
    INACTIVE("I");

    private final String code;

    StatusCode(String statusCode) {
        this.code = statusCode;
    }

    public String getCode() {
        return code;
    }
}
