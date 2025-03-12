package com.codechallenge.application.rest1;

import com.codechallenge.hexagon.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> userDtoList = users.stream().map(user -> {
            return new UserDto(user.username(),user.name(),user.email(),user.gender(),user.pic());
        }).toList();
        return ResponseEntity.ok(userDtoList);
    };

    @GetMapping("/api/users/{username}/")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return ResponseEntity.ok(userService.getUser(username));
    };

}
