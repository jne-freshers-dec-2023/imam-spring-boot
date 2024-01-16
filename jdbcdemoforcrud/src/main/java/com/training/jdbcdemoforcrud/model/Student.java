package com.training.jdbcdemoforcrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "std_id")
    private int id;

    @Column(name = "std_name")
    private String name;

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "fk_add")
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
}
