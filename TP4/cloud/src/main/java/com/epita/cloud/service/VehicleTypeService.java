package com.epita.cloud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.cloud.dto.VehicleTypeDTO;
import com.epita.cloud.model.VehicleType;
import com.epita.cloud.repository.VehicleTypeRepository;

@Service
public class VehicleTypeService {
    
    @Autowired
    public VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleTypeDTO> getAllVehicleTypes()
    {
        List<VehicleTypeDTO> res = vehicleTypeRepository.findAll().stream()
            .map(vehicleType -> new VehicleTypeDTO(
                vehicleType.getId(),
                vehicleType.getName(),
                vehicleType.getCapacity(),
                vehicleType.getManufacturer(),
                vehicleType.getCompany()
            ))
            .collect(Collectors.toList());
        return res;
    }

    public Integer getNbVehicleType(String vehicleTypeName)
    {
        //count
        return vehicleTypeRepository.findAllVehicleType(vehicleTypeName).size();
    }

    public Optional<VehicleType> findvehicleByID(Integer VtID)
    {
        return vehicleTypeRepository.findById(VtID);
    }

    public VehicleType save(VehicleType vt)
    {
        vehicleTypeRepository.save(vt);
        return vt;
    }

    public Optional<VehicleType> checkVTExistence(String name)
    {
        return (vehicleTypeRepository.checkVTExistence(name));
    }

}
