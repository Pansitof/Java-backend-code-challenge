package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.CantGenerateZeroUsersException;
import com.codechallenge.application.usecase.exception.EmailAlreadyInUseException;

import java.util.List;
import java.util.Optional;

public class UserGenerate {
    private UserRepository userRepository;

    public UserGenerate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(int i) {
        Optional<List<User>> users = userRepository.generateUsers(i);
        if (users.isEmpty()) {
            throw new CantGenerateZeroUsersException();
        }
        for (User user : users.get()){
            userRepository.createUser(user);
        }
    }
}
