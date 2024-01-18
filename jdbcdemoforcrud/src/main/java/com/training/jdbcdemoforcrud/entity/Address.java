package com.training.jdbcdemoforcrud.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

@Data
@Entity(name = "address")
public class Address {
    @Id
    @GenericGenerator(name = "UUIDGenerator")
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.UUID)
    @Column(name = "add_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin")
    private int pin;
}
