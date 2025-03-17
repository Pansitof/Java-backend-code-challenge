package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UsersFinderShould {

    @Mock
    private UserRepository userRepository;
    private UsersFinder usersFinder = new UsersFinder(userRepository);


    @Test
    public void notFindUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepository);
        Mockito.when(userRepository.getAll()).thenReturn(List.of());

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(0, users.size());
    }

    @Test
    public void findOneUserWithExpectedData() {
        //Arrange
        usersFinder = new UsersFinder(userRepository);
        Mockito.when(userRepository.getAll()).thenReturn(List.of(
                UserMother.createUser("username", "name", "email", "gender", "picture")));

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(1, users.size());
    }

    @Test
    public void findsMultipleUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepository);
        Mockito.when(userRepository.getAll()).thenReturn(List.of(
                UserMother.createUser("username", "name", "email", "gender", "picture"),
                UserMother.createUser("UserB", "UserB", "UserB", "UserB", "UserB"),
                UserMother.createUser("UserC", "UserC", "UserC", "UserC", "UserC"),
                UserMother.createUser("UserD", "UserD", "UserD", "UserD", "UserD")));

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(4, users.size());
    }


}
