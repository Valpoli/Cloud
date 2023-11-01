package com.epita.cloud.dto;

public class CreateCompDTO {
    private String name;

    public CreateCompDTO() {
    }
    
    public CreateCompDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


