package com.codechallenge.application;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException() {
        super("Username already exist");
    }

}
