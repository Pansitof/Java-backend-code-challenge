package com.codechallenge.application.usecase;

import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserPostShould {
    @Mock
    private UserRepository userRepository;
    private UserPost userPost;

    @BeforeEach
    void setup() {
        userPost = new UserPost(userRepository);
    }

    @Test
    public void failByUserNotExisting(){
        String usernameTest = "TestUsername";
        Mockito.when(userRepository.getById(usernameTest)).thenReturn(Optional.empty());

        //Act & assert
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userPost.execute(usernameTest);
        });

        assertEquals("There isn't an user with that ID", exception.getMessage());
    }

    @Test
    public void failByIncorrectEmail(){

    }

    @Test
    public void failByEmailAlreadyUsed(){

    }
}
