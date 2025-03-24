package com.codechallenge.application.usecase;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.CantGenerateZeroUsersException;
import com.codechallenge.application.usecase.exception.EmailInvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserGenerateShould {
    @Mock
    private UserRepository userRepository;
    private UserGenerate userGenerate;

    @BeforeEach
    void setup() {
        userGenerate = new UserGenerate(userRepository);
    }

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void createValidUsersAsIndicated() {
        User userPedro = UserMother.createUser("Pedro", "Pedro", "Pedro@Pedro.es", "Pedro", "Pedro");
        User userMartin = UserMother.createUser("Martin", "Martin", "Martin@Martin.es", "Martin", "Martin");
        User userSalome = UserMother.createUser("Salome", "Salome", "Salome@Salome.es", "Salome", "Salome");
        User userMaria = UserMother.createUser("Maria", "Maria", "Maria@Maria.es", "Maria", "Maria");
        Mockito.when(userRepository.generateUsers(4)).thenReturn(Optional.of(List.of(
                userPedro,
                userMartin,
                userSalome,
                userMaria)));

        userGenerate.execute(4);

        Mockito.verify(userRepository, Mockito.times(4)).createUser(userCaptor.capture());
        List<User> Users = userCaptor.getAllValues();
        assertAll(
                "Grouped Assertions of Generated Randomly Users",
                () -> assertEquals(4, Users.size()),
                () -> assertEquals(userPedro, Users.get(0)),
                () -> assertEquals(userMartin, Users.get(1)),
                () -> assertEquals(userSalome, Users.get(2)),
                () -> assertEquals(userMaria, Users.get(3))
        );
    }

    @Test
    public void failByAskingToCreateZeroUsers() {
        Mockito.when(userRepository.generateUsers(0)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CantGenerateZeroUsersException.class, () -> {
            userGenerate.execute(0);
        });

        assertEquals("It's not possible to generate Zero users", exception.getMessage());
    }

}
