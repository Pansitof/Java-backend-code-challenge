package com.codechallenge.technologies.restapi.adapters.mysql;

import com.codechallenge.application.User;
import com.codechallenge.application.ports.driven.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class SpringBootMySqlUserRepository implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    public SpringBootMySqlUserRepository(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userEntity ->{
           return new User(userEntity.username(),userEntity.name(),userEntity.email(),userEntity.gender(),userEntity.picture());
        }).toList();
    }

    @Override
    public User getById(String username) {
        return new User("username", "name", "email", "gender", "picture");
    }
}
