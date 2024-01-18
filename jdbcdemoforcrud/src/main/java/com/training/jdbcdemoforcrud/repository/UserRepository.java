package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    @Transactional
    void deleteByUuid(UUID uuid);
}
