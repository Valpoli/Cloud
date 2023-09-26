package com.epita.cloud.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epita.cloud.model.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType,Integer> {
}