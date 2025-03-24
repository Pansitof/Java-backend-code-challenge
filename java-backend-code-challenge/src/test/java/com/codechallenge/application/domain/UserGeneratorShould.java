package com.codechallenge.application.domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratorShould {
    private UserGenerator userGenerator;

    @Test
    public void generateRandomUser() {
        userGenerator = new UserGenerator();
        User user;
        try {
            user = userGenerator.generateAnUser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertAll(
                "Grouped Assertions of Generated User",
                () -> assertFalse(user.username().isBlank()),
                () -> assertFalse(user.name().isBlank()),
                () -> assertFalse(user.email().isBlank()),
                () -> assertFalse(user.gender().isBlank()),
                () -> assertFalse(user.picture().isBlank())
        );
    }
}
