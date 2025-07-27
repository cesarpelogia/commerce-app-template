package com.storage.model.service;

import java.util.List;

import com.storage.model.dto.UserTypeNameDTO;
import com.storage.model.entity.UserTypeName;

public interface UserTypeNameService {

    UserTypeName createUserTypeName(UserTypeNameDTO userTypeName);
    List<UserTypeNameDTO> getAllUserTypeNames();
    UserTypeName getUserTypeNameById(Long id);
    UserTypeName updateUserTypeName(UserTypeNameDTO userTypeName);
    void deleteUserTypeName(Long id);

}
