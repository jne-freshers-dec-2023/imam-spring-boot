package com.training.jdbcdemoforcrud.repository;

import com.training.jdbcdemoforcrud.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
