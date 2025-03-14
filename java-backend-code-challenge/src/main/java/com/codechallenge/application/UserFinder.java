package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

class UserFinder {
    private UserRepository repository;

    public UserFinder(UserRepository repository){
        this.repository = repository;
    }

    public User execute(String username) {
        return repository.getById(username);
    }

}
