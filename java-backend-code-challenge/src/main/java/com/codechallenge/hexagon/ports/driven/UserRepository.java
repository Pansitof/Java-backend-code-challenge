package com.codechallenge.hexagon.ports.driven;

import com.codechallenge.hexagon.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
}
