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

import java.util.List;
import java.util.regex.Pattern;

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
    public void failByEmailIncorrectWhenUserIsBeingCreated() {

        Exception exception = assertThrows(EmailInvalidFormatException.class, () -> {
            userCreator.execute("TestUsername", "name", "INCORRECTEMAILFORMAT", "gender");
        });

        assertEquals("Email has Incorrect Format", exception.getMessage());
    }

    @Test
    public void failByUsernameAlreadyExist() {
        String testUsername = "TestUsername";

        Mockito.when(userRepository.getById(testUsername)).thenReturn(UserMother.createUser(testUsername, "name", "Wewew@wewW.es", "gender", "picture"));

        Exception exception = assertThrows(UsernameAlreadyExistException.class, () -> {
            userCreator.execute(testUsername, "name", "email@email.es", "gender");
        });
        assertEquals("Username already exist", exception.getMessage());

        Mockito.verify(userRepository, Mockito.never()).createUser(Mockito.any(User.class));
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
        Mockito.when(numberGenerator.generateFourRandomsDigits()).thenReturn(pictureCodeGenerated);

        String username = "Pedro";
        userCreator.execute(username, "name", "email@email.es", "gender");

        Mockito.verify(userRepository).createUser(userCaptor.capture());
        User userCaptured = userCaptor.getValue();
        String expectedPicture = username+"_"+pictureCodeGenerated;
        assertEquals(expectedPicture, userCaptured.picture());
    }

    @Test
    public void failByEmailAlreadyUsed() {
        Mockito.when(userRepository.getByEmail("Wewew@wewW.es")).thenReturn(UserMother.createUser("testUsername", "testName", "Wewew@wewW.es", "testGender", "testPicture"));

        Exception exception = assertThrows(EmailAlreadyInUseException.class, () -> {
            userCreator.execute("TestUsername", "name", "Wewew@wewW.es", "gender");
        });

        assertEquals("That email is already in use by another account", exception.getMessage());
    }

}


//Necesitaría el UsersFinder para asegurarse de que picture no se repita.
//Un test que compruebe que en caso de que se repita, no se cree o se vuelve a generar un número aleatorio
//Comprobar que el email no sea el mismo que uno ya existente --- DONE
//