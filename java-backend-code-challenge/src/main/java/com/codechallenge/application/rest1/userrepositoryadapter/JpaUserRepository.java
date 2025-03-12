package com.codechallenge.application.rest1.userrepositoryadapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
}
