package com.codechallenge.application.ports.driven;

import com.codechallenge.application.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();

    void createUser(User user);

    Optional<User> getByEmail(String email);

    Optional<User> getById(String username);
}
