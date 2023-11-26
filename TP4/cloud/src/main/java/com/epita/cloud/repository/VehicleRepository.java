package com.epita.cloud.repository;

import java.sql.Time;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epita.cloud.dto.VehicleDTO;
import com.epita.cloud.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    @Modifying
    @Query("UPDATE Vehicle v SET v.pnb = :pnb, v.lastMsgDate = :lastMsgDate WHERE v.vin = :vin")
    void updateVehicleInfo(@Param("vin") String vin, @Param("pnb") int pnb, @Param("lastMsgDate") Time lastMsgDate);

}
