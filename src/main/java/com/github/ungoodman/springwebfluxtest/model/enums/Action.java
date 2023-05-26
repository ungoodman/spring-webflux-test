package com.github.ungoodman.springwebfluxtest.model.enums;

public enum Action {
    ACTIVE(Constants.ACTIVE),
    DELETE(Constants.DELETE),
    INACTIVE(Constants.INACTIVE),
    SAVE(Constants.SAVE),
    UPDATE(Constants.UPDATE)
    ;

    private final String act;

    Action(String action) {
        this.act = action;
    }

    public String getAction() {
        return this.act;
    }

    private static class Constants {
        private static final String SAVE = "SAVE";
        private static final String UPDATE = "UPDATE";
        private static final String DELETE = "DELETE";
        private static final String ACTIVE = "ACTIVE";
        private static final String INACTIVE = "INACTIVE";
    }
}
