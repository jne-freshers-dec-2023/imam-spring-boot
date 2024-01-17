package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
