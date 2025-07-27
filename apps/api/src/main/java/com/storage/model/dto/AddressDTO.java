package com.storage.model.dto;

import lombok.Data;

@Data
public class AddressDTO {
    
    private String street;
    private Integer number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}