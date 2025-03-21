package com.codechallenge.application.usecase;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UserPostShould {
    @Mock
    private UserRepository userRepository;
    private UserPost userPost;

    @BeforeEach
    void setup() {
        userPost = new UserPost();
    }

    @Test
    public void failByUsernameNotExisting(){

    }

    @Test
    public void failByIncorrectEmail(){

    }

    @Test
    public void failByEmailAlreadyUsed(){

    }
}
