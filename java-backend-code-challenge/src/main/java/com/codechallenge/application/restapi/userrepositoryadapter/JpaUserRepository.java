package com.codechallenge.application.restapi.userrepositoryadapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
}
