package com.epita.cloud.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VEHICLE_TYPE")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seqVehicleType")
    @GenericGenerator(name = "seqVehicleType", strategy="increment")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public VehicleType() {
    }

    public VehicleType(String name, Integer capacity, String manufacturer, Company company) {
        this.name = name;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
