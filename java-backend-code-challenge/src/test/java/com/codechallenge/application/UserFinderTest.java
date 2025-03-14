package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepositoryStub;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFinderTest {

    private UserRepository userRepositoryNoUsers = new UserRepositoryStub(List.of());
    private UserRepository userRepositoryWithUsers = new UserRepositoryStub(List.of(
            new User("username", "name", "email", "gender", "picture"),
            new User("UserB", "UserB", "UserB", "UserB", "UserB"),
            new User("UserC", "UserC", "UserC", "UserC", "UserC"),
            new User("UserD", "UserD", "UserD", "UserD", "UserD")
    ));

    @Test
    public void doesNotFoundUser_returnNull() {
        //Arrange
        UserFinder userFinder = new UserFinder(userRepositoryNoUsers);

        //Act
        var user = userFinder.execute("NO USER WITH THIS USERNAME");

        //assert
        assertEquals(null, user);
    }


    @Test
    public void doesFoundUser_returnUser() {
        //Arrange
        UserFinder userFinder = new UserFinder(userRepositoryWithUsers);

        //Act
        var user = userFinder.execute("UserB");

        //assert
        assertEquals("UserB", user.username());
    }

}


