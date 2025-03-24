package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.UserNotFoundException;

import java.util.Optional;

public class UserDelete {
    private UserRepository repository;

    public UserDelete(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String username){
        Optional<User> searchedUser = repository.getById(username);
        if (searchedUser.isEmpty()) {
            throw new UserNotFoundException("There isn't an user with that ID");
        }
        repository.deleteUser(searchedUser.get());
    }
}
