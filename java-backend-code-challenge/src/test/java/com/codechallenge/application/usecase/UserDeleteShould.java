package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
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

@ExtendWith(MockitoExtension.class)
public class UserDeleteShould {

    @Mock
    private UserRepository userRepository;
    private UserDelete userDelete;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setup() {
        userDelete = new UserDelete(userRepository);
    }

    @Test
    public void deleteAnUser(){
        String username = "username";
        Optional<User> user = Optional.of(UserMother.createUser(username, "testName", "testEmail@email.es", "testGender", "testPicture"));
        Mockito.when(userRepository.getById(username)).thenReturn(user);

        userDelete.execute(username);

        Mockito.verify(userRepository).deleteUser(userCaptor.capture());
        User userCaptured = userCaptor.getValue();

        assertAll(
                "Grouped Assertions of Expected deleted User data",
                () -> assertEquals(username, userCaptured.username()),
                () -> assertEquals("testName", userCaptured.name()),
                () -> assertEquals("testEmail@email.es", userCaptured.email()),
                () -> assertEquals("testGender", userCaptured.gender()),
                () -> assertEquals("testPicture", userCaptured.picture())
        );

    }

    @Test
    public void failByUserNotExisting(){
        String usernameTest = "TestUsername";
        Mockito.when(userRepository.getById(usernameTest)).thenReturn(Optional.empty());

        //Act & assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userDelete.execute(usernameTest);
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());
    }


}
