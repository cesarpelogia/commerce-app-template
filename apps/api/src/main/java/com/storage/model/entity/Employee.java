package com.storage.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_full_name", nullable = false)
    private String employeeFullName;

    @Column(name = "employee_display_name", nullable = false)
    private String employeeDisplayName;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "employee_phone", unique = true)
    private String employeePhone;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type_id")
    private EmployeeType employeeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_status_id")
    private EmployeeStatus employeeStatus;
    
    @OneToMany(mappedBy = "employee")
    private List<EmployeeStore> employeeStores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

}
