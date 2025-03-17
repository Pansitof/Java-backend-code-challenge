package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;

class UserCreator {
    private UserRepository repository;

    public UserCreator(UserRepository repository){
        this.repository = repository;
    }


    public boolean execute(User user) {
        return repository.createUser(user);
    }

}
