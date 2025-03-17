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
public class UserManagerShould {

    @Mock
    private UserRepository userRepository;
    private UserFinder userFinder = new UserFinder(userRepository);
    private UsersFinder usersFinder = new UsersFinder(userRepository);
    private UserCreator userCreator = new UserCreator(userRepository);

    //UserCreator
    @Test
    public void failByEmailIncorrectWhenUserIsBeingCreated() {
        userCreator = new UserCreator(userRepository);

        Exception exception = assertThrows(EmailInvalidFormatException.class, () -> {
            userCreator.execute("TestUsername","name", "INCORRECTEMAILFORMAT", "gender");
        });

        assertEquals("Email has Incorrect Format", exception.getMessage());
    }

    @Test
    public void failByUsernameAlreadyExist() {
        userCreator = new UserCreator(userRepository);
        String testUsername = "TestUsername";

        Mockito.when(userRepository.getById(testUsername)).thenReturn(createUser(testUsername,"name","Wewew@wewW.es","gender","picture"));

        Exception exception = assertThrows(UsernameAlreadyExistException.class, () -> {
            userCreator.execute(testUsername,"name", "email@email.es", "gender");
        });

        assertEquals("Username already exist", exception.getMessage());
    }

    @Test
    public void createAnUserWithSpecifiedData() {
        //Arrange
        userCreator = new UserCreator(userRepository);

        //Act
        userCreator.execute("TestUsername","name", "email@email.es", "gender");

        //assert
        Mockito.verify(userRepository).createUser(Mockito.any(User.class));
    }

    //UserFinder
    @Test
    public void notFindUserById() {
        //Arrange
        String leUser = "leUser";
        userFinder = new UserFinder(userRepository);
        Mockito.when(userRepository.getById(leUser)).thenReturn(null);

        //Act & assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userFinder.execute(leUser);
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());

    }

    @Test
    public void findUserByIdWithExpectedData() {
        //Arrange
        userFinder = new UserFinder(userRepository);
        String testName = "TestName";
        String testEmail = "TestEmail";
        String testGender = "TestGender";
        String testPicture = "TestPicture";
        String testUsername = "TestUsername";
        Mockito.when(userRepository.getById(testUsername)).thenReturn(createUser(testUsername, testName, testEmail, testGender, testPicture));

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

    //UsersFinder
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
                createUser("username", "name", "email", "gender", "picture")));

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
