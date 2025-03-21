package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.EmailAlreadyInUseException;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.usecase.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
public class UserPut {
    private UserRepository repository;

    public UserPut(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String username, String name, String email, String gender) {
        Optional<User> searchedUser = repository.getById(username);
        if (searchedUser.isEmpty()) {
            throw new UserNotFoundException("There isn't an user with that ID");
        }
        if (repository.getByEmail(email).isPresent()) {
            throw new EmailAlreadyInUseException();
        }
        Pattern allowedEmailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
        if (!allowedEmailPattern.matcher(email).find() && !email.isEmpty()) {
            throw new EmailInvalidFormatException("Email has Incorrect Format");
        }
        String newName = searchedUser.get().name();
        String newEmail = searchedUser.get().email();
        String newGender = searchedUser.get().gender();
        if (!name.isEmpty()) {
            newName = name;
        }
        if (!email.isEmpty()) {
            newEmail = email;
        }
        if (!gender.isEmpty()) {
            newGender = gender;
        }

        User newUser = new User(username, newName, newEmail, newGender, searchedUser.get().picture());


        repository.modifyUser(newUser);
    }


}
