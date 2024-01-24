package com.symbiosis.studentservice.repository;

import com.symbiosis.studentservice.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student,UUID> {

    Optional<Student> findByUuid(UUID uuid);

    @Modifying
    @Transactional
    int deleteByUuid(UUID uuid);

 //   @Query("Select s from student s ORDER BY :orderBy DESC")
 @Query(value = "SELECT s FROM student s")
 List<Student> findStudentsWithPaginationOrderBy(Pageable pageable);

}
