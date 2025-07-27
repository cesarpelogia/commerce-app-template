package com.storage.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storage.model.dto.UserTypeNameDTO;
import com.storage.model.entity.UserTypeName;
import com.storage.model.service.UserTypeNameService;

@RestController
@RequestMapping("/user-types")
public class UserTypeController {

    @Autowired
    private UserTypeNameService userTypeService;

    @PostMapping
    public ResponseEntity<UserTypeName> createUserType(@RequestBody UserTypeNameDTO userTypeName) {
        UserTypeName createdUserType = userTypeService.createUserTypeName(userTypeName);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserType);
    }
}