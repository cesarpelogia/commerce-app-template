package com.storage.model.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storage.model.dto.AddressDTO;
import com.storage.model.entity.Address;
import com.storage.model.repository.AddressRepository;
import com.storage.model.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(AddressDTO addressDTO) {

        addressDTO = validateAddress(addressDTO);

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setDistrict(addressDTO.getDistrict());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setCountry(addressDTO.getCountry());
        
        return addressRepository.save(address);
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
            Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com o ID: " + id));

            // Só atualiza se o campo não for null
            if (addressDTO.getStreet() != null) address.setStreet(addressDTO.getStreet());
            if (addressDTO.getNumber() != null) address.setNumber(addressDTO.getNumber());
            if (addressDTO.getComplement() != null) address.setComplement(addressDTO.getComplement());
            if (addressDTO.getDistrict() != null) address.setDistrict(addressDTO.getDistrict());
            if (addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
            if (addressDTO.getState() != null) address.setState(addressDTO.getState());
            if (addressDTO.getZipCode() != null) address.setZipCode(addressDTO.getZipCode());
            if (addressDTO.getCountry() != null) address.setCountry(addressDTO.getCountry());

            addressRepository.save(address);

            // Retorna o DTO atualizado
            AddressDTO updatedDTO = new AddressDTO();
            updatedDTO.setStreet(address.getStreet());
            updatedDTO.setNumber(address.getNumber());
            updatedDTO.setComplement(address.getComplement());
            updatedDTO.setDistrict(address.getDistrict());
            updatedDTO.setCity(address.getCity());
            updatedDTO.setState(address.getState());
            updatedDTO.setZipCode(address.getZipCode());
            updatedDTO.setCountry(address.getCountry());

            return updatedDTO;
    }

    @Override
    public void deleteAddress(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if (!addressRepository.existsById(id)) {
            throw new IllegalArgumentException("Endereço não encontrado com o ID: " + id);
        }
        addressRepository.deleteById(id);
    }
   
    @Override
    public AddressDTO findAddressById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com o ID: " + id));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setCountry(address.getCountry());

        return addressDTO;        

    }

    @Override
    public List<AddressDTO> findAllAddresses() {

        List<Address> addresses = addressRepository.findAll();

        if (addresses == null || addresses.isEmpty()) {
            return List.of();
        }
        
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(address -> {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setStreet(address.getStreet());
                    addressDTO.setNumber(address.getNumber());
                    addressDTO.setComplement(address.getComplement());
                    addressDTO.setDistrict(address.getDistrict());
                    addressDTO.setCity(address.getCity());
                    addressDTO.setState(address.getState());
                    addressDTO.setZipCode(address.getZipCode());
                    addressDTO.setCountry(address.getCountry());
                    return addressDTO;
                })
                .toList();
        return addressDTOs;
    }

    private AddressDTO validateAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new IllegalArgumentException("O endereço não pode ser nulo");
        }
        if (addressDTO.getStreet() == null || addressDTO.getStreet().isEmpty()) {
            throw new IllegalArgumentException("A rua não pode ser nula ou vazia");
        }
        if (addressDTO.getNumber() <= 0) {
            throw new IllegalArgumentException("O número deve ser maior que zero");
        }
        if (addressDTO.getCity() == null || addressDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("A cidade não pode ser nula ou vazia");
        }
        if (addressDTO.getState() == null || addressDTO.getState().isEmpty()) {
            throw new IllegalArgumentException("O estado não pode ser nulo ou vazio");
        }

        if (addressDTO.getZipCode() == null || addressDTO.getZipCode().isEmpty()) {
            throw new IllegalArgumentException("O CEP não pode ser nulo ou vazio");
        }
        return addressDTO;
    }

}
