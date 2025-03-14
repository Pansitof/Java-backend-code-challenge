package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepositoryMockNoUsers;
import com.codechallenge.application.ports.driven.UserRepositoryMockWithUsers;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersFinderTest {

    private UserRepository userRepositoryNoUsers = new UserRepositoryMockNoUsers();
    private UserRepository userRepositoryWithUsers = new UserRepositoryMockWithUsers();


    @Test
    public void doesNotContainUsers_returnEmptyUserList() {
        //Arrange
        UsersFinder usersFinder = new UsersFinder(userRepositoryNoUsers);

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(0, users.size());
    }

    @Test
    public void doesContainUsers_returnUserList() {
        //Arrange
        UsersFinder usersFinder = new UsersFinder(userRepositoryWithUsers);

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(4, users.size());
    }


}


