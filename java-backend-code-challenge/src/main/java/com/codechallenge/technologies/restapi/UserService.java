package com.codechallenge.technologies.restapi;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.UserManager;
import com.codechallenge.application.ports.driven.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserManager userManager;

    public UserService(UserRepository repository) {
        this.userManager = new UserManager(repository);
    }

    public List<User> getUsers() {
        return userManager.getUsers();
    }

    public User getUser(String username) {
        return userManager.getUserById(username);
    }

    public void createUser(String username, String name, String email, String gender) {
        userManager.createUser(username,name,email,gender);
    }
}
