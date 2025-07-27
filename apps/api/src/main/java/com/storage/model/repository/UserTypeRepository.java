package com.storage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storage.model.entity.UserTypeName;

public interface UserTypeRepository extends JpaRepository<UserTypeName, Long> {


}
