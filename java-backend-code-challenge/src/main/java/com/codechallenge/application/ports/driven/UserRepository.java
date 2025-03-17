package com.codechallenge.application.ports.driven;

import com.codechallenge.application.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User getById(String username);

    User createUser(User user);
}
