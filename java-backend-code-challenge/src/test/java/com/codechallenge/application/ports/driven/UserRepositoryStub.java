package com.codechallenge.application.ports.driven;

import com.codechallenge.application.User;

import java.util.List;

public class UserRepositoryStub implements UserRepository {

    private List<User> users;

    public UserRepositoryStub(List<User> users) {
        this.users = users;
    }

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

    @Override
    public boolean createUser(User user) {
        return false;
    }
}