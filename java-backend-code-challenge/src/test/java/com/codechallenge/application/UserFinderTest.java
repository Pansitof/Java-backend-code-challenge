package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepositoryMockNoUsers;
import com.codechallenge.application.ports.driven.UserRepositoryMockWithUsers;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFinderTest {

    private UserRepository userRepositoryNoUsers = new UserRepositoryMockNoUsers();
    private UserRepository userRepositoryWithUsers = new UserRepositoryMockWithUsers();



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


