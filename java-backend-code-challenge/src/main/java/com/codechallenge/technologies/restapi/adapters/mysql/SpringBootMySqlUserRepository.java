package com.codechallenge.technologies.restapi.adapters.mysql;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.domain.UserGenerator;
import com.codechallenge.application.ports.driven.UserRepository;
import com.codechallenge.application.usecase.exception.CantGenerateZeroUsersException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class SpringBootMySqlUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public SpringBootMySqlUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userEntity -> {
            return new User(userEntity.username(), userEntity.name(), userEntity.email(), userEntity.gender(), userEntity.picture());
        }).toList();
    }

    @Override
    public void createUser(User user) {
        System.out.println(user);
        jpaUserRepository.save(convertUserToUserEntity(user));
    }

    @Override
    public void modifyUser(User user) {
        jpaUserRepository.save(convertUserToUserEntity(user));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        for (UserEntity user : userEntities) {
            if (user.email().equals(email)) {
                return Optional.of(new User(user.username(), user.name(), user.email(), user.gender(), user.picture()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(String username) {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        for (UserEntity user : userEntities) {
            if (user.username().equals(username)) {
                return Optional.of(new User(user.username(), user.name(), user.email(), user.gender(), user.picture()));
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(User user) {
        jpaUserRepository.delete(convertUserToUserEntity(user));
    }

    @Override
    public Optional<List<User>> generateUsers(int i) {
        List<User> users = new ArrayList<>();
        UserGenerator userGenerator = new UserGenerator();
        for (int j = 0; j < i; j++) {
            try {
                users.add(userGenerator.generateAnUser());
            } catch (IOException e) {
                throw new CantGenerateZeroUsersException();
            }
        }
        return Optional.of(users);
    }

    public UserEntity convertUserToUserEntity(User user) {
        return new UserEntity(user.username(), user.name(), user.email(), user.gender(), user.picture());
    }
}
