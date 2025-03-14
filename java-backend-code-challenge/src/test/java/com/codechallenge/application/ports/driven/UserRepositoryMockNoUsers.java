package com.codechallenge.application.ports.driven;

import com.codechallenge.application.User;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMockNoUsers implements UserRepository {
    List<User> users = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(String username) {
        return null;
    }
}