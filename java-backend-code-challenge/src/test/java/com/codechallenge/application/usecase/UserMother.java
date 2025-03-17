package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;

public class UserMother {
    static User createUser(String testUsername, String testName, String testEmail, String testGender, String testPicture) {
        return new User(testUsername, testName, testEmail, testGender, testPicture);
    }
}
