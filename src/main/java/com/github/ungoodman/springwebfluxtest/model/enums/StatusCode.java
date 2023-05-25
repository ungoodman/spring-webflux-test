package com.github.ungoodman.springwebfluxtest.model.enums;

public enum StatusCode {
    ACTIVE(Constants.ACTIVE),
    DELETED(Constants.DELETED),
    INACTIVE(Constants.INACTIVE);

    private final String code;

    StatusCode(String statusCode) {
        this.code = statusCode;
    }

    public String getCode() {
        return code;
    }

    private static class Constants {
        private static final String ACTIVE = "A";
        private static final String DELETED = "D";
        private static final String INACTIVE = "I";
    }
}
