package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.NumberGenerator;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.exception.UsernameAlreadyExistException;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.Objects;
import java.util.regex.Pattern;

class UserCreator {
    private UserRepository repository;
    private NumberGenerator numberGenerator;

    public UserCreator(UserRepository repository, NumberGenerator numberGenerator) {
        this.repository = repository;
        this.numberGenerator = numberGenerator;
    }

    public void execute(String testUsername, String name, String email, String gender) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
        if(!pattern.matcher(email).find()) {
            throw new EmailInvalidFormatException("Email has Incorrect Format");
        }

        User existingUser = repository.getById(testUsername);
        if (Objects.nonNull(existingUser)){
            throw new UsernameAlreadyExistException();
        }
        String picture = testUsername+"_"+numberGenerator.generateFourRandomsDigits();
        repository.createUser(new User(testUsername, name, email, gender, picture));
    }
}
