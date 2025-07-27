package com.storage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storage.model.entity.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

}
