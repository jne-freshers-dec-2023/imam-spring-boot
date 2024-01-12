package com.training.jdbcdemoforcrud.model;

import jakarta.persistence.*;

@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "std_id")
    private int id;

    @Column(name = "std_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", address=" + address +
                '}';
    }
}
