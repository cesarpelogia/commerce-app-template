package com.storage.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_full_name", nullable = false)
    private String supplierFullName;

    @Column(name = "supplier_display_name", nullable = false)
    private String supplierDisplayName;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;

    @Column(name = "supplier_phone", nullable= false, unique = true)
    private String supplierPhone;

    @Column(name = "supplier_email", nullable = false, unique = true)
    private String supplierEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

}
