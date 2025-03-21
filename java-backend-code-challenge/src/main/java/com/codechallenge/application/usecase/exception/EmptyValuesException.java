package com.codechallenge.application.usecase.exception;

public class EmptyValuesException extends RuntimeException {
    public EmptyValuesException(String message) {
        super(message);
    }
}
