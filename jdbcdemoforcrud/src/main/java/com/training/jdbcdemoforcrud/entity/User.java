package com.training.jdbcdemoforcrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity(name = "Users")
@Data
public class User {

    @Id
    @UuidGenerator
    private UUID uuid;

    @Column(name = "user_name")
    String name;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    String role;
}
