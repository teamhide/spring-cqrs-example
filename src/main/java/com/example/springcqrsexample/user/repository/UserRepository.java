package com.example.springcqrsexample.user.repository;

import com.example.springcqrsexample.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    boolean existsByEmail(String email);
}
