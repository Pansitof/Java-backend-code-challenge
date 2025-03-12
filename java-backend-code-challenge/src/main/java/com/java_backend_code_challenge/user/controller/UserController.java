package com.java_backend_code_challenge.user.controller;

import com.java_backend_code_challenge.user.model.User;
import com.java_backend_code_challenge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getListUsers(){
        StringBuilder SB = new StringBuilder();
        for (User user : userService.get_List_of_Users()){
            SB.append(user.toString());
        }
        return SB.toString();
    };

    @GetMapping("/api/users/")
    public String getIndividualUser(@PathVariable String username){
        return userService.get_User(username).toString();
    };


    @PostMapping("/api/users/")
    public String postUser(@RequestBody User user){
        return userService.save_new_user(user).toString();
    };

}
