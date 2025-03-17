package com.codechallenge.application.usecase.exception;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException() {
        super("Username already exist");
    }

}
