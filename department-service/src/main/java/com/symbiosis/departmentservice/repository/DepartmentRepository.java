package com.symbiosis.departmentservice.repository;

import com.symbiosis.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Optional<Department> findByName(String name);

    Optional<Department> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    void deleteByUuid(UUID uuid);
}
