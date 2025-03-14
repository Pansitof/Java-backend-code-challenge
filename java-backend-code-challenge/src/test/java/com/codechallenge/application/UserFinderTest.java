package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepositoryStub;
import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserFinderTest {

    @Mock
    private UserRepository userRepositoryStub;

    @Test
    public void doesNotFoundUser_returnNull() {
        //Arrange
        Mockito.when(userRepositoryStub.getById("leUser")).thenReturn(null);
        UserFinder userFinder = new UserFinder(userRepositoryStub);

        //Act
        var user = userFinder.execute("leUser");

        //assert
        assertEquals(null, user);
    }


    @Test
    public void doesFoundUser_returnUser() {
        //Arrange
        UserFinder userFinder = new UserFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getById("UserB")).thenReturn(new User("UserB", "UserB", "UserB", "UserB", "UserB"));

        //Act
        var user = userFinder.execute("UserB");

        //assert
        assertEquals("UserB", user.username());
    }

}


