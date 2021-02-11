package com.ezerio.ipinfo.exceptions;

public class ErrorMessage {

    private final String error;
    private final String message;
    private final String path;

    public ErrorMessage(String path) {
        this.error = "Internal server error";
        this.message = "Try again later";
        this.path = path;
    }

    public ErrorMessage(Exception exception, String path) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    public ErrorMessage(String error, String message, String path) {
        this.error = error;
        this.message = message;
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
