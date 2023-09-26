package com.epita.cloud.dto;

import java.sql.Date;

public class VehicleDTO {
    private Integer id;
    private String vin;
    private Date lastMsgDate;
    private Integer pnb;
    private Integer vehicleTypeId;

    public VehicleDTO() {
    }

    public VehicleDTO(Integer id, String vin, Date lastMsgDate, Integer pnb, Integer vehicleTypeId) {
        this.id = id;
        this.vin = vin;
        this.lastMsgDate = lastMsgDate;
        this.pnb = pnb;
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(Date lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }

    public Integer getPnb() {
        return pnb;
    }

    public void setPnb(Integer pnb) {
        this.pnb = pnb;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}

