package com.symbiosis.studentservice.repository;

import com.symbiosis.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student,UUID> {

    Optional<Student> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    void deleteByUuid(UUID uuid);
}
