package com.ezerio.ipinfo.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorValidationMessage {

    private final String error;
    private final String message;
    private final String path;

    public ErrorValidationMessage(MethodArgumentNotValidException exception, String path) {
        final FieldError fielderror = exception.getBindingResult().getFieldError();
        this.message = fielderror.getDefaultMessage();
        this.error = "ValidationException";
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
