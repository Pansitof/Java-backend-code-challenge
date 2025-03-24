package com.codechallenge.application.usecase;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTreeShould {
    @Mock
    private UserRepository userRepository;
    private UserTree userTree;
}
