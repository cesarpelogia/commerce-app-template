package com.storage.model.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storage.model.dto.UserTypeNameDTO;
import com.storage.model.entity.UserTypeName;
import com.storage.model.repository.UserTypeRepository;
import com.storage.model.service.UserTypeNameService;

@Service
public class UserTypeNameServiceImpl implements UserTypeNameService{

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public UserTypeName createUserTypeName(UserTypeNameDTO userTypeNameDTO) {
        if (userTypeNameDTO == null || userTypeNameDTO.getUserTypeName()== null || userTypeNameDTO.getUserTypeName().isEmpty()) {
            throw new IllegalArgumentException("O tipo de usuário não pode ser nulo ou vazio.");
        }

        UserTypeName newUserType = new UserTypeName();
        newUserType.setUserTypeName(userTypeNameDTO.getUserTypeName());

        return userTypeRepository.save(newUserType);
    }

    @Override
    public List<UserTypeNameDTO> getAllUserTypeNames() {
        List<UserTypeName> userTypes = userTypeRepository.findAll();

        if (userTypes == null || userTypes.isEmpty()) {
            throw new IllegalArgumentException("Nenhum tipo de usuário encontrado.");
        }

        List<UserTypeNameDTO> userTypeNames = userTypes.stream()
                .map(userType -> {
                    UserTypeNameDTO dto = new UserTypeNameDTO();
                    dto.setId(userType.getId());
                    dto.setUserTypeName(userType.getUserTypeName());
                    return dto;
                })
                .toList();
        return userTypeNames; // Placeholder return statement
    }

    @Override
    public UserTypeName getUserTypeNameById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do tipo de usuário não pode ser nulo.");
        }
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário não encontrado com o ID: " + id));
    }

    @Override
    public UserTypeName updateUserTypeName(UserTypeNameDTO userTyperNameDTO) {
        if (userTyperNameDTO == null || userTyperNameDTO.getId() == null) {
            throw new IllegalArgumentException("O tipo de usuário e seu ID não podem ser nulos.");
        }

        UserTypeName existingUserType = userTypeRepository.findById(userTyperNameDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário não encontrado com o ID: " + userTyperNameDTO.getId()));

        existingUserType.setUserTypeName(userTyperNameDTO.getUserTypeName());

        return userTypeRepository.save(existingUserType);
    }
    
    @Override
    public void deleteUserTypeName(Long id) {
       if (id == null) {
            throw new IllegalArgumentException("O ID do tipo de usuário não pode ser nulo.");
        }
        if (!userTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Tipo de usuário não encontrado com o ID: " + id);
        }
        userTypeRepository.deleteById(id);
    }

}
