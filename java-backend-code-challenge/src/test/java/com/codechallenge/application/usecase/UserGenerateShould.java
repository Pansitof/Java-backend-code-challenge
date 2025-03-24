package com.codechallenge.application.usecase;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserGenerateShould {
    @Mock
    private UserRepository userRepository;
    private UserGenerate userGenerate;

    @BeforeEach
    void setup() {
        userGenerate = new UserGenerate(userRepository);
    }
    @Test
    public void createAsMuchUsersAsIndicated(){
        userRepository.generateUsers(5);
    }
}
