package com.codechallenge.application.usecase;

import com.codechallenge.application.ports.driven.UserRepository;

public class UserGenerate {
    private UserRepository userRepository;

    public UserGenerate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(){

    }
}
