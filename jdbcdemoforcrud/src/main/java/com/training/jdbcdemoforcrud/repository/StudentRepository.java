package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    @Transactional
    void deleteByUuid(UUID uuid);
}
