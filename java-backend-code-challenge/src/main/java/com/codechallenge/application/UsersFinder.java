package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

public class UsersFinder {
    private UserRepository repository;

    public UsersFinder(UserRepository repository){
        this.repository = repository;
    }

    public List<User> execute() {
        return repository.getAll();
    }
}
