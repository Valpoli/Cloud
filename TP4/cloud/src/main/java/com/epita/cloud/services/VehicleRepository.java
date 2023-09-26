package com.epita.cloud.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epita.cloud.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
