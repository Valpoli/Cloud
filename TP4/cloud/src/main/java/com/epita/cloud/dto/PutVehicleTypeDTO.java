package com.epita.cloud.dto;

public class PutVehicleTypeDTO {


    private Integer vtID;
    private String vehicleTypeName;
    private Integer passengerNumber;

    public Integer getVtID() {
        return vtID;
    }

    public void setVtID(Integer vtID) {
        this.vtID = vtID;
    }
    
    public Integer getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(Integer passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
    
}
