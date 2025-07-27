package com.storage.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee_store")
@Data
public class EmployeeStore {

    @EmbeddedId
    private EmployeeStoreId id = new EmployeeStoreId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storeId")
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

}
