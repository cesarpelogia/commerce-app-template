package com.storage.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_full_name", nullable = false)
    private String storeFullName;

    @Column(name = "store_display_name", nullable = false)
    private String storeDisplayName;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "store_phone", nullable = false, unique = true)
    private String storePhone;

    @Column(name = "store_email", nullable = false, unique = true)
    private String storeEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store")
    private List<EmployeeStore> employeeStores;

}