package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.usecase.exception.UserNotFoundException;

import java.util.Optional;
import java.util.regex.Pattern;

public class UserPut {
    private UserRepository repository;

    public UserPut(UserRepository repository){
        this.repository = repository;
    }

    public void execute(String username, String name, String email, String gender) {
        Optional<User> searchedUser = repository.getById(username);
        if (searchedUser.isEmpty()){
            throw new UserNotFoundException("There isn't an user with that ID");
        }
        Pattern allowedEmailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
        if(!allowedEmailPattern.matcher(email).find()) {
            throw new EmailInvalidFormatException("Email has Incorrect Format");
        }
        User newUser = new User(username,name,email,gender,searchedUser.get().picture());

        repository.modifyUser(newUser);
    }



}
