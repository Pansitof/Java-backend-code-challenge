package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.NumberGenerator;
import com.codechallenge.application.usecase.exception.EmailAlreadyInUseException;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.exception.UsernameAlreadyExistException;
import com.codechallenge.application.ports.driven.UserRepository;
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
public class UserCreatorShould {

    @Mock
    private UserRepository userRepository;
    @Mock
    private NumberGenerator numberGenerator;

    private UserCreator userCreator;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setup() {
        userCreator = new UserCreator(userRepository, numberGenerator);
    }

    @Test
    public void createAnUserWithSpecifiedData() {
        //Arrange
        String username = "username";
        String name = "name";
        String email = "email@email.es";
        String gender = "gender";

        //Act
        userCreator.execute(username, name, email, gender);
        Mockito.verify(userRepository).createUser(userCaptor.capture());

        //assert
        User userCaptured = userCaptor.getValue();

        assertAll(
                "Grouped Assertions of Expected User data",
                () -> assertFalse(userCaptured.picture().isBlank()),
                () -> assertEquals(username, userCaptured.username()),
                () -> assertEquals(name, userCaptured.name()),
                () -> assertEquals(email, userCaptured.email()),
                () -> assertEquals(gender, userCaptured.gender())
        );
    }

    @Test
    public void beCreatedWithAnAllowedPicture() {
        int pictureCodeGenerated = 1111;
        String username = "Pedro";
        Mockito.when(numberGenerator.generateFourRandomsDigits()).thenReturn(pictureCodeGenerated);

        userCreator.execute(username, "name", "email@email.es", "gender");

        Mockito.verify(userRepository).createUser(userCaptor.capture());
        User userCaptured = userCaptor.getValue();
        String expectedPicture = username+"_"+pictureCodeGenerated;
        assertEquals(expectedPicture, userCaptured.picture());
    }

    @Test
    public void failByEmailIncorrectWhenUserIsBeingCreated() {

        Exception exception = assertThrows(EmailInvalidFormatException.class, () -> {
            userCreator.execute("TestUsername", "name", "INCORRECTEMAILFORMAT", "gender");
        });

        assertEquals("Email has Incorrect Format", exception.getMessage());
    }

    @Test
    public void failByUsernameAlreadyExist() {
        String testUsername = "TestUsername";

        User user = UserMother.createUser(testUsername, "name", "Wewew@wewW.es", "gender", "picture");
        Mockito.when(userRepository.getById(testUsername)).thenReturn(Optional.of(user));

        Exception exception = assertThrows(UsernameAlreadyExistException.class, () -> {
            userCreator.execute(testUsername, "name", "email@email.es", "gender");
        });
        assertEquals("Username already exist", exception.getMessage());
        Mockito.verify(userRepository, Mockito.never()).createUser(Mockito.any(User.class));
    }

    @Test
    public void failByEmailAlreadyUsed() {
        User user = UserMother.createUser("testUsername", "testName", "Wewew@wewW.es", "testGender", "testPicture");
        Mockito.when(userRepository.getByEmail("Wewew@wewW.es")).thenReturn(Optional.of(user));

        Exception exception = assertThrows(EmailAlreadyInUseException.class, () -> {
            userCreator.execute("TestUsername", "name", "Wewew@wewW.es", "gender");
        });

        assertEquals("That email is already in use by another account", exception.getMessage());
    }
}