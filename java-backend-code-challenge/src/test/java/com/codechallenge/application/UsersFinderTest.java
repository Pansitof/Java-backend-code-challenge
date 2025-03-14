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
public class UsersFinderTest {

    @Mock
    private UserRepository userRepositoryStub;

    @Test
    public void doesNotContainUsers() {
        //Arrange
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of());

        UsersFinder usersFinder = new UsersFinder(userRepositoryStub);

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(0, users.size());
    }

    @Test
    public void doesContainUsers() {
        //Arrange
        Mockito.when(userRepositoryStub.getAll()).thenReturn(List.of(
                new User("username", "name", "email", "gender", "picture"),
                new User("UserB", "UserB", "UserB", "UserB", "UserB"),
                new User("UserC", "UserC", "UserC", "UserC", "UserC"),
                new User("UserD", "UserD", "UserD", "UserD", "UserD")));
        UsersFinder usersFinder = new UsersFinder(userRepositoryStub);

        //Act
        var users = usersFinder.execute();

        //assert
        assertEquals(4, users.size());
    }

    @Test
    public void doesUsersMatch() {
        //Arrange

        UsersFinder usersFinder = new UsersFinder(userRepositoryStub);
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


