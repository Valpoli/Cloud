package com.epita.cloud.dto;

public class PutVehicleTypeDTO {


    private Integer vtID;
    private String vehicleTypeName;

    public Integer getVtID() {
        return vtID;
    }

    public void setVtID(Integer vtID) {
        this.vtID = vtID;
    }
    
    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
    
}
