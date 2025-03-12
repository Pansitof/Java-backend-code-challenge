package com.java_backend_code_challenge.user.controller;

import com.java_backend_code_challenge.user.model.User;
import com.java_backend_code_challenge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    };

    @GetMapping("/api/users/{username}/")
    public String getIndividualUser(@PathVariable String username){
        return userService.get_User(username).toString();
    };

}
