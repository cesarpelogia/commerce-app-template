package com.storage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
