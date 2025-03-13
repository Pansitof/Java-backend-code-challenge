package com.codechallenge.application.restapi;

import com.codechallenge.hexagon.User;
import com.codechallenge.hexagon.UsersFinder;
import com.codechallenge.hexagon.ports.driven.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        UsersFinder usersFinder = new UsersFinder(repository);
        return usersFinder.execute();
    }

    public User getUser(String username) {
        return null;
    }

}
