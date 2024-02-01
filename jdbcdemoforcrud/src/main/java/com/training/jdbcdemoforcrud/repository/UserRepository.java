package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);
}
