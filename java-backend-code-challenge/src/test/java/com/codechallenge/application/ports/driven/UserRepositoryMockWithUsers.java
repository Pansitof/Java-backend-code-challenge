package com.codechallenge.application.ports.driven;

import com.codechallenge.application.User;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

public class UserRepositoryMockWithUsers implements UserRepository {
    private List<User> users = List.of(
            new User("username", "name", "email", "gender", "picture"),
            new User("UserB", "UserB", "UserB", "UserB", "UserB"),
            new User("UserC", "UserC", "UserC", "UserC", "UserC"),
            new User("UserD", "UserD", "UserD", "UserD", "UserD")
    );
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(String username) {
        for(User user : users){
            if (user.username().equals(username)){
                return user;
            }
        }
        return null;
    }
}