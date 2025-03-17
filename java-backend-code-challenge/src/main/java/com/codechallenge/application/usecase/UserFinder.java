package com.codechallenge.application.usecase;

import com.codechallenge.application.User;
import com.codechallenge.application.UserNotFoundException;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.Objects;

class UserFinder {
    private UserRepository repository;

    public UserFinder(UserRepository repository){
        this.repository = repository;
    }

    public User execute(String username) {
        User resultedUser = repository.getById(username);
        if (Objects.isNull(resultedUser)){
            throw new UserNotFoundException("There isn't an user with that ID");
        }
        return resultedUser;
    }

}
