package com.epita.cloud;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;

public class StudentDTO {
    @Length(min = 3, max = 256)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}