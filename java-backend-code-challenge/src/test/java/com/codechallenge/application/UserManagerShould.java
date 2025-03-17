package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String message) {
        super(message);
    }
}

@ExtendWith(MockitoExtension.class)
public class UserManagerShould {

    @Mock
    private UserRepository userRepositoryStub;
    private UserFinder userFinder = new UserFinder(userRepositoryStub);
    private UsersFinder usersFinder = new UsersFinder(userRepositoryStub);

    @Test
    public void notFindUserById() {
        //Arrange
        userFinder = new UserFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getById("leUser")).thenThrow(new UserNotFoundException("There isn't an user with that ID"));

        //Act

        //assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userFinder.execute("leUser");
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());

    }

    @Test
    public void findUserByIdWithExpectedData() {
        //Arrange
        userFinder = new UserFinder(userRepositoryStub);
        String testName = "TestName";
        String testEmail = "TestEmail";
        String testGender = "TestGender";
        String testPicture = "TestPicture";
        String testUsername = "TestUsername";
        Mockito.when(userRepositoryStub.getById(testUsername)).thenReturn(createUser(testUsername, testName, testEmail, testGender, testPicture));

        //Act
        User resultedUser = userFinder.execute(testUsername);

        //assert
        assertNotNull(resultedUser);
        assertEquals(testName, resultedUser.name(), "name value");
        assertEquals(testEmail, resultedUser.email(), "email value");
        assertEquals(testGender, resultedUser.gender(), "gender value");
        assertEquals(testPicture, resultedUser.picture(), "picture value");
        assertEquals(testUsername, resultedUser.username(), "username value");
    }


    @Test
    public void notFindUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of());

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(0, users.size());
    }

    @Test
    public void findOneUserWithExpectedData() {
        //Arrange
        usersFinder = new UsersFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of(
                createUser("username", "name", "email", "gender", "picture")));

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(1, users.size());
    }

    @Test
    public void findsMultipleUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of(
                createUser("username", "name", "email", "gender", "picture"),
                createUser("UserB", "UserB", "UserB", "UserB", "UserB"),
                createUser("UserC", "UserC", "UserC", "UserC", "UserC"),
                createUser("UserD", "UserD", "UserD", "UserD", "UserD")));

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(4, users.size());
    }


    private static User createUser(String testUsername, String testName, String testEmail, String testGender, String testPicture) {
        return new User(testUsername, testName, testEmail, testGender, testPicture);
    }
}
