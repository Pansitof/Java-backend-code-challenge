package com.codechallenge.technologies.restapi.adapters.mysql;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        for (UserEntity user : userEntities){
            if(user.username().equals(username)){
                return new User(user.username(),user.name(),user.email(),user.gender(),user.picture());
            }
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        return new User("","","","","");
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> getById2(String username) {
        return Optional.empty();
    }
}
