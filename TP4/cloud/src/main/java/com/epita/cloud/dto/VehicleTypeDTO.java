package com.epita.cloud.dto;

import com.epita.cloud.model.Company;

public class VehicleTypeDTO {
    private int id;
    private String name;
    private int capacity;
    private String manufacturer;
    private Company companyName;

    public VehicleTypeDTO(int id, String name, int capacity, String manufacturer, Company company) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.companyName = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
