package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.exception.UserNotFoundException;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.Objects;
import java.util.Optional;

class UserFinder {
    private UserRepository repository;

    public UserFinder(UserRepository repository){
        this.repository = repository;
    }

    public User execute(String username) {
        Optional<User> resultedUser = repository.getById2(username);
        if (resultedUser.isEmpty()){
            throw new UserNotFoundException("There isn't an user with that ID");
        }
        return resultedUser.get();
    }

}
