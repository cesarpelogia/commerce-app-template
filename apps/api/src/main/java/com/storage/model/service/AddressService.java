package com.storage.model.service;

import java.util.List;

import com.storage.model.dto.AddressDTO;
import com.storage.model.entity.Address;

public interface AddressService {
    
    Address createAddress(AddressDTO addressDTO);
    AddressDTO updateAddress(Long id, AddressDTO addressDTO);
    void deleteAddress(Long id);
    AddressDTO findAddressById(Long id);
    List<AddressDTO> findAllAddresses();

}
