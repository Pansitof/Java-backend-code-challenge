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
    public void doesNotFoundUser() {
        //Arrange
        Mockito.when(userRepositoryStub.getById("leUser")).thenReturn(null);
        UserFinder userFinder = new UserFinder(userRepositoryStub);

        //Act
        var user = userFinder.execute("leUser");

        //assert
        assertEquals(null, user);
    }


    @Test
    public void doesFoundUser() {
        //Arrange
        UserFinder userFinder = new UserFinder(userRepositoryStub);
        User userInStub = new User("TestUsername", "TestName", "TestEmail", "TestGender", "TestPicture");
        Mockito.when(userRepositoryStub.getById("TestUsername")).thenReturn(userInStub);

        //Act
        User user = userFinder.execute("TestUsername");

        //assert
        assertEquals(userInStub,user);
    }

    @Test
    public void doesValuesMatch() {
        //Arrange
        UserFinder userFinder = new UserFinder(userRepositoryStub);
        User userInStub = new User("TestUsername", "TestName", "TestEmail", "TestGender", "TestPicture");
        Mockito.when(userRepositoryStub.getById("TestUsername")).thenReturn(userInStub);
        //Act
        var user = userFinder.execute("TestUsername");

        //assert
        assertEquals("TestUsername", user.username(), "username value");
        assertEquals("TestName", user.name(), "name value");
        assertEquals("TestEmail", user.email(), "email value");
        assertEquals("TestGender", user.gender(), "gender value");
        assertEquals("TestPicture", user.picture(), "picture value");
    }

}


