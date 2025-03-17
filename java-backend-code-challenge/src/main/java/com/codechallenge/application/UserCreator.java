package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailInvalidFormatException extends RuntimeException {
    public EmailInvalidFormatException(String message) {
        super(message);
    }
}

class UserCreator {
    private UserRepository repository;

    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String testUsername, String name, String email, String gender) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
        if(!pattern.matcher(email).find()) {
            throw new EmailInvalidFormatException("Email has Incorrect Format");
        }
        repository.createUser(new User(testUsername, name, email, gender, "picture"));
    }
}
