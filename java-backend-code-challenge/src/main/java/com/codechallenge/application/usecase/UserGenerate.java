package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
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
        List<User> generatedRandomlyUsers = users.get();
        for (User user : generatedRandomlyUsers){
            userRepository.createUser(user);
        }
    }
}
