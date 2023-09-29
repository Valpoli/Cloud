package com.epita.cloud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epita.cloud.model.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType,Integer> {

    @Query("SELECT v.id FROM Vehicle v " +
           "JOIN v.vehicleType vt " +
           "WHERE vt.name LIKE :vehicleTypeName")
    List<Integer> findAllVehicleType(String vehicleTypeName);

    @Query("SELECT name FROM VehicleType vt WHERE vt.name LIKE :newVTName")
    Optional<VehicleType> checkVTExistence(String newVTName);
}