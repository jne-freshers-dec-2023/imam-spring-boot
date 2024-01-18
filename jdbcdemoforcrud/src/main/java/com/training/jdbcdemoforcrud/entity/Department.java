package com.training.jdbcdemoforcrud.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
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
