package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.EmailAlreadyInUseException;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.usecase.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class UserPutShould {
    @Mock
    private UserRepository userRepository;
    private UserPut userPut;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setup() {
        userPut = new UserPut(userRepository);
    }

    @Test
    public void updateAnUserWithNewData() {
        String username = "testUsername";
        String newName = "newName";
        String newEmail = "newEmail@newEmail.es";
        String newGender = "newGender";
        User oldUser = UserMother.createUser(username, "testName", "testEmail@email.es", "testGender", "testPicture");
        Mockito.when(userRepository.getById(username)).thenReturn(Optional.of(oldUser));

        userPut.execute(username, newName, newEmail, newGender);

        Mockito.verify(userRepository).modifyUser(userCaptor.capture());
        User userCaptured = userCaptor.getValue();

        assertAll(
                "Grouped Assertions of Expected modified User data",
                () -> assertEquals(username, userCaptured.username()),
                () -> assertEquals(newName, userCaptured.name()),
                () -> assertEquals(newEmail, userCaptured.email()),
                () -> assertEquals(newGender, userCaptured.gender()),
                () -> assertEquals("testPicture", userCaptured.picture())
        );
    }

    @Test
    public void onlyUpdateSomeValues(){
        String username = "testUsername";
        String newGender = "newGender";
        User oldUser = UserMother.createUser(username, "testName", "testEmail@email.es", "testGender", "testPicture");
        Mockito.when(userRepository.getById(username)).thenReturn(Optional.of(oldUser));

        userPut.execute(username, "", "", newGender);

        Mockito.verify(userRepository).modifyUser(userCaptor.capture());
        User userCaptured = userCaptor.getValue();

        assertAll(
                "Grouped Assertions of Expected modified User data",
                () -> assertEquals(username, userCaptured.username()),
                () -> assertEquals("testName", userCaptured.name()),
                () -> assertEquals("testEmail@email.es", userCaptured.email()),
                () -> assertEquals(newGender, userCaptured.gender()),
                () -> assertEquals("testPicture", userCaptured.picture())
        );
    }

    @Test
    public void failByUserNotExisting() {
        String usernameTest = "TestUsername";
        Mockito.when(userRepository.getById(usernameTest)).thenReturn(Optional.empty());

        //Act & assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userPut.execute(usernameTest, "","","");
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());
    }

    @Test
    public void failByIncorrectEmail() {
        String username = "username";
        User oldUser = UserMother.createUser(username, "testName", "wawa", "testGender", "testPicture");
        Mockito.when(userRepository.getById(username)).thenReturn(Optional.of(oldUser));

        Exception exception = assertThrows(EmailInvalidFormatException.class, () -> {
            userPut.execute(username,"","wawa","");
        });

        assertEquals("Email has Incorrect Format", exception.getMessage());
    }

    @Test
    public void failByEmailAlreadyUsed() {
        String username = "testUsername";
        String email = "testEmail@email.es";
        User oldUser = UserMother.createUser(username, "testName", email, "testGender", "testPicture");
        Mockito.when(userRepository.getByEmail(email)).thenReturn(Optional.of(oldUser));
        Mockito.when(userRepository.getById(username)).thenReturn(Optional.of(oldUser));

        Exception exception = assertThrows(EmailAlreadyInUseException.class, () -> {
            userPut.execute(username,"", email,"");
        });

        assertEquals("That email is already in use by another account", exception.getMessage());
    }
}
