package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.exception.UserNotFoundException;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserFinderShould {

    @Mock
    private UserRepository userRepository;
    private UserFinder userFinder;

    @BeforeEach
    void setup() {
        userFinder = new UserFinder(userRepository);
    }

    @Test
    public void findUserByIdWithExpectedData() {
        //Arrange
        String testName = "TestName";
        String testEmail = "TestEmail";
        String testGender = "TestGender";
        String testPicture = "TestPicture";
        String testUsername = "TestUsername";
        User user = UserMother.createUser(testUsername, testName, testEmail, testGender, testPicture);
        Mockito.when(userRepository.getById2(testUsername)).thenReturn(Optional.of(user));

        //Act
        User resultedUser = userFinder.execute(testUsername);

        //assert
        assertAll(
                "Grouped Assertions of Expected User data",
                () -> assertNotNull(resultedUser),
                () -> assertEquals(testName, resultedUser.name(), "name value"),
                () -> assertEquals(testEmail, resultedUser.email(), "email value"),
                () -> assertEquals(testGender, resultedUser.gender(), "gender value"),
                () -> assertEquals(testPicture, resultedUser.picture(), "picture value"),
                () -> assertEquals(testUsername, resultedUser.username(), "username value")
        );

    }

    @Test
    public void notFindUserById() {
        //Arrange
        String usernameTest = "leUser";
        Mockito.when(userRepository.getById2(usernameTest)).thenReturn(Optional.empty());

        //Act & assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userFinder.execute(usernameTest);
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());

    }

}
