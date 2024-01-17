package com.training.jdbcdemoforcrud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_id")
    private int id;

    @Column(name = "dept_name")
    private String name;
}
