package com.epita.cloud.dto;

public class CreateVTDTO {
    private String name;
    private Integer capacity;
    private String manufacturer;
    private Integer companyId;

    public CreateVTDTO(String name, Integer capacity, String manufacturer, Integer companyId) {
        this.name = name;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.companyId = companyId;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    
}
