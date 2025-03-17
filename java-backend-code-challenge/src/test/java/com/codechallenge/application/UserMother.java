package com.codechallenge.application;

public class UserMother {
    static User createUser(String testUsername, String testName, String testEmail, String testGender, String testPicture) {
        return new User(testUsername, testName, testEmail, testGender, testPicture);
    }
}
