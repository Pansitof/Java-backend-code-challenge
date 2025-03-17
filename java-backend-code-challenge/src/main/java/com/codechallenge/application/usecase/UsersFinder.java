package com.codechallenge.application.usecase;

import com.codechallenge.application.User;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

class UsersFinder {
    private UserRepository repository;

    public UsersFinder(UserRepository repository){
        this.repository = repository;
    }

    public List<User> execute() {
        return repository.getAll();
    }
}
