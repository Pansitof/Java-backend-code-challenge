package com.codechallenge.adapters.userrepository.filesystem;

import com.codechallenge.application.User;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;

public class FileSystemUserRepository implements UserRepository {

    @Override
    public List<User> getAll() {
        return List.of(new User("username","name","email","gender","picture"));
    }

}
