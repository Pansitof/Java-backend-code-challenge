package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.NumberGenerator;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import com.codechallenge.application.domain.User;
import com.codechallenge.application.usecase.exception.UsernameAlreadyExistException;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserCreatorShould {

    @Mock
    private UserRepository userRepository;
    private UserCreator userCreator;

    @BeforeEach
    void setup(){
        userCreator = new UserCreator(userRepository, new NumberGenerator());
    }

    @Test
    public void failByEmailIncorrectWhenUserIsBeingCreated() {

        Exception exception = assertThrows(EmailInvalidFormatException.class, () -> {
            userCreator.execute("TestUsername","name", "INCORRECTEMAILFORMAT", "gender");
        });

        assertEquals("Email has Incorrect Format", exception.getMessage());
    }

    @Test
    public void failByUsernameAlreadyExist() {
        String testUsername = "TestUsername";

        Mockito.when(userRepository.getById(testUsername)).thenReturn(UserMother.createUser(testUsername,"name","Wewew@wewW.es","gender","picture"));

        Exception exception = assertThrows(UsernameAlreadyExistException.class, () -> {
            userCreator.execute(testUsername,"name", "email@email.es", "gender");
        });
        assertEquals("Username already exist", exception.getMessage());

        Mockito.verify(userRepository,Mockito.never()).createUser(Mockito.any(User.class));
    }

    @Test
    public void createAnUserWithSpecifiedData() {
        //Arrange

        //Act
        userCreator.execute("TestUsername","name", "email@email.es", "gender");

        //assert
        Mockito.verify(userRepository).createUser(Mockito.any(User.class));
    }


}
