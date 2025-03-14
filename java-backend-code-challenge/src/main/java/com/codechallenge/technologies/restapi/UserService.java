package com.codechallenge.technologies.restapi;

import com.codechallenge.application.User;
import com.codechallenge.application.UserManager;
import com.codechallenge.application.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
