package com.codechallenge.application;

import com.codechallenge.application.ports.driven.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserManagerTest {

    @Mock
    private UserRepository userRepositoryStub;
    private UserFinder userFinder = new UserFinder(userRepositoryStub);
    private UsersFinder usersFinder = new UsersFinder(userRepositoryStub);

    @Test
    public void doesNotFoundUser() {
        //Arrange
        userFinder = new UserFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getById("leUser")).thenReturn(null);

        //Act
        var user = userFinder.execute("leUser");

        //assert
        assertEquals(null, user);
    }

    @Test
    public void doesFoundUser() {
        //Arrange
        userFinder = new UserFinder(userRepositoryStub);
        User userInStub = new User("TestUsername", "TestName", "TestEmail", "TestGender", "TestPicture");
        Mockito.when(userRepositoryStub.getById("TestUsername")).thenReturn(userInStub);

        //Act
        User user = userFinder.execute("TestUsername");

        //assert
        assertEquals(userInStub,user);
    }

    @Test
    public void doesFoundUserValuesMatch() {
        //Arrange
        userFinder = new UserFinder(userRepositoryStub);
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


    @Test
    public void doesNotContainUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of());

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(0, users.size());
    }

    @Test
    public void doesContainUsers() {
        //Arrange
        usersFinder = new UsersFinder(userRepositoryStub);
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of(
                new User("username", "name", "email", "gender", "picture"),
                new User("UserB", "UserB", "UserB", "UserB", "UserB"),
                new User("UserC", "UserC", "UserC", "UserC", "UserC"),
                new User("UserD", "UserD", "UserD", "UserD", "UserD")));

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(4, users.size());
    }

    @Test
    public void doesUsersMatch() {
        //Arrange

        usersFinder = new UsersFinder(userRepositoryStub);
        List<User> userInStub = List.of(
                new User("username", "name", "email", "gender", "picture"),
                new User("UserB", "UserB", "UserB", "UserB", "UserB"),
                new User("UserC", "UserC", "UserC", "UserC", "UserC"),
                new User("UserD", "UserD", "UserD", "UserD", "UserD"));
        Mockito.when(userRepositoryStub.getAll()).thenReturn(userInStub);
        //Act
        var users = usersFinder.execute();
        //assert
        assertEquals(userInStub.get(0), users.get(0));
        assertEquals(userInStub.get(1), users.get(1));
        assertEquals(userInStub.get(2), users.get(2));
        assertEquals(userInStub.get(3), users.get(3));
    }
}
