package com.codechallenge.application.usecase.exception;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException() {
        super("That email is already in use by another account");
    }
}
