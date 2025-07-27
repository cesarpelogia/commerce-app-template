package com.storage.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "country", nullable = false)
    private String country;

}
