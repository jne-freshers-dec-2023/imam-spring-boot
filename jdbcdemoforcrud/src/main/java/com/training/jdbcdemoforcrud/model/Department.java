package com.training.jdbcdemoforcrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_id")
    private int id;

    @Column(name = "dept_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Student> student;
}
