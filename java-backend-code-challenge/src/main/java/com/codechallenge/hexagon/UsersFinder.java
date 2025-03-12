package com.codechallenge.hexagon;

import com.codechallenge.hexagon.ports.driven.UserRepository;

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
