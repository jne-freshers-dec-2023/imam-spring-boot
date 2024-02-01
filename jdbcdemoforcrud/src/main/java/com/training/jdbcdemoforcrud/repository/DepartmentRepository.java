package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
    Optional<Department> findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    @Transactional
    void deleteByUuid(UUID uuid);
}
