package com.github.ungoodman.springwebfluxtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GenericBadException extends RuntimeException{
    public GenericBadException() {
        super();
    }

    public GenericBadException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBadException(String message) {
        super(message);
    }

    public GenericBadException(Throwable cause) {
        super(cause);
    }
}
