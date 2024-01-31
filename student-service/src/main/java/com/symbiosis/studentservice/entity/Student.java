package com.symbiosis.studentservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity(name = "student")
public class Student {

    @Id
    @UuidGenerator
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "std_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_add")
    private Address address;

    @Column(name = "fk_dept")
    private UUID deptId;
}
