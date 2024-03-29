package com.epita.cloud.dto;

import com.epita.cloud.model.Company;

public class VehicleTypeDTO {
    private Integer id;
    private String name;
    private Integer capacity;
    private String manufacturer;
    private Company companyName;

    public VehicleTypeDTO(Integer id, String name, Integer capacity, String manufacturer, Company company) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.companyName = company;
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

    public Company getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Company companyName) {
        this.companyName = companyName;
    }
}
