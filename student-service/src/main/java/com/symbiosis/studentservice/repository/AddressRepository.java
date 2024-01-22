package com.symbiosis.studentservice.repository;

import com.symbiosis.studentservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
