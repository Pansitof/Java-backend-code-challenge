package com.codechallenge.application.usecase.exception;

public class CantGenerateZeroUsersException extends RuntimeException {
    public CantGenerateZeroUsersException() {
        super("It's not possible to generate Zero users");
    }
}
