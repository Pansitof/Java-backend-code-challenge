package com.java_backend_code_challenge.user.service;

import com.java_backend_code_challenge.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java_backend_code_challenge.user.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private userRepository repository;

    public List<User> get_List_of_Users() {
        return repository.findAll();
    }

    public User get_User(String username) {
        return repository.findById(username).get();
    }
}
