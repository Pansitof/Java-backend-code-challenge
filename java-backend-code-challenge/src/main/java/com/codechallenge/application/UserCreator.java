package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

import java.util.Objects;
import java.util.regex.Pattern;

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

        User existingUser = repository.getById(testUsername);
        if (Objects.nonNull(existingUser)){
            throw new UsernameAlreadyExistException();
        }
        repository.createUser(new User(testUsername, name, email, gender, "picture"));
    }
}
