package com.symbiosis.departmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_id")
    int deptId;
    @Column(name = "dept_name")
    String deptName;
}
