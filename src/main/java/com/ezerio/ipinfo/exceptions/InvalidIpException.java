package com.ezerio.ipinfo.exceptions;

public class InvalidIpException extends BadRequestException {
    public InvalidIpException() {
        super("Invalid ip");
    }

    public static class NotFoundException extends RuntimeException {

        private static final String DESCRIPTION = "Not Found Exception (404)";

        public NotFoundException(String detail) {
            super(DESCRIPTION + ". " + detail);
        }

    }
}
