package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.domain.NumberGenerator;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

public class UserManager {

    private final UserRepository repository;

    public UserManager(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return new UsersFinder(repository).execute();
    }

    public User getUserById(String username) {
        return new UserFinder(repository).execute(username);
    }

    public void createUser(String Username, String name, String email, String gender) {
        new UserCreator(repository, new NumberGenerator()).execute(Username, name, email, gender);
    }

    public void modifyUser(String Username, String name, String email, String gender) {
        new UserPut(repository).execute(Username, name, email, gender);
    }

    public void deleteUser(String username) {
        new UserDelete(repository).execute(username);
    }

}
