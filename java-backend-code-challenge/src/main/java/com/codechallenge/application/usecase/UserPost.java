package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.UserNotFoundException;

import java.util.Optional;

public class UserPost {
    private UserRepository repository;

    public UserPost(UserRepository repository){
        this.repository = repository;
    }

    public void execute(String username) {
        Optional<User> searchedUser = repository.getById(username);

        if (searchedUser.isEmpty()){
            throw new UserNotFoundException("There isn't an user with that ID");
        }
    }

}
