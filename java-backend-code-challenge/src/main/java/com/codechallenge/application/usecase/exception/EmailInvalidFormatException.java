package com.codechallenge.application.usecase.exception;

public class EmailInvalidFormatException extends RuntimeException {
    public EmailInvalidFormatException(String message) {
        super(message);
    }
}
