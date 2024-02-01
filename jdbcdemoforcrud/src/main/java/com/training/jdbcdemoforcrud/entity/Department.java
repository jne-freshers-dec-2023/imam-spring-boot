package com.training.jdbcdemoforcrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity(name = "department")
public class Department {
    @Id
    @UuidGenerator
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dept_name")
    private String name;
}
