package com.codechallenge.application.restapi.userrepositoryadapter;

import com.codechallenge.hexagon.User;
import com.codechallenge.hexagon.ports.driven.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlUserRepository implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    public MySqlUserRepository(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userEntity ->{
           return new User(userEntity.username(),userEntity.name(),userEntity.email(),userEntity.gender(),userEntity.picture());
        }).toList();
    }
}
