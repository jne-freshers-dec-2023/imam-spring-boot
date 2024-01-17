package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
