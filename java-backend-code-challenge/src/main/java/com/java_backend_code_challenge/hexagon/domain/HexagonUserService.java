package com.java_backend_code_challenge.hexagon.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HexagonUserService {

    @Autowired
    private HexagonUserRepository repository;

    public HexagonUser find_user_by_id(String username) {
        return repository.findById(username).get();
    }

    public List<HexagonUser> find_all_users(){
        return repository.findAll();
    }
}
