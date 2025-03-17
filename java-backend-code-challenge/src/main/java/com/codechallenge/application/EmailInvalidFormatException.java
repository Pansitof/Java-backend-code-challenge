package com.codechallenge.application;

class EmailInvalidFormatException extends RuntimeException {
    public EmailInvalidFormatException(String message) {
        super(message);
    }
}
