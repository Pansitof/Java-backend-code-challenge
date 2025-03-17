package com.codechallenge.application;

public class EmailInvalidFormatException extends RuntimeException {
    public EmailInvalidFormatException(String message) {
        super(message);
    }
}
