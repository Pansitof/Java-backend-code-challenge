package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

public class UserManager {

    private final UserRepository repository;

    public UserManager(UserRepository repository){
        this.repository=repository;
    }

    public List<User> getUsers(){
        return new UsersFinder(repository).execute();
    }

    public User getUserById(String username) {
        return new UserFinder(repository).execute(username);
    }

}
