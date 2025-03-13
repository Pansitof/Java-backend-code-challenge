package com.codechallenge.technologies.restapi;

import com.codechallenge.application.User;
import com.codechallenge.application.UsersFinder;
import com.codechallenge.application.ports.driven.UserRepository;
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
