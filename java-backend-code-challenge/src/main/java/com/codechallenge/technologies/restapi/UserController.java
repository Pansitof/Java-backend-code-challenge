package com.codechallenge.technologies.restapi;

import com.codechallenge.application.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDto> userDtoList = users.stream().map(user -> {
            return new UserDto(user.username(), user.name(), user.email(), user.gender(), user.picture());
        }).toList();
        return ResponseEntity.ok(userDtoList);
    }


    @GetMapping("/api/users/{username}/")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUser(username));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/users/")
    public ResponseEntity postUser(@RequestBody UserCreate user) {
        try {
            userService.createUser(user.username(), user.name(), user.email(), user.gender());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/api/users/{username}/")
    public ResponseEntity putUser(@PathVariable String username,@RequestBody UserUpdate user){
        try {
            userService.updateUser(username,user.name(),user.email(),user.gender());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            if (e.getMessage().equals("There isn't an user with that ID")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/users/{username}/")
    public ResponseEntity deleteUser(@PathVariable String username){
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            if (e.getMessage().equals("There isn't an user with that ID")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }


}
