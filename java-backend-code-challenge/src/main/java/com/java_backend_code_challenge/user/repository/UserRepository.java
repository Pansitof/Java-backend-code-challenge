package com.java_backend_code_challenge.user.repository;

import com.java_backend_code_challenge.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
