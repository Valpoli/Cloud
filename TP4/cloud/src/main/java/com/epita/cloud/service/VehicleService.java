package com.epita.cloud.service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epita.cloud.dto.TelematicsDTO;
import com.epita.cloud.dto.VehicleDTO;
import com.epita.cloud.repository.VehicleRepository;
import java.util.Date;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public String modifyVehicleInfos(TelematicsDTO telematicsData) {
        long millis = Long.parseLong(telematicsData.getLocalTime());
        Date date = new Date(millis);
        Time time = new Time(date.getTime());

        vehicleRepository.updateVehicleInfo(telematicsData.getVehicleId(), telematicsData.getPassengerNumber(), time);
        return telematicsData.getVehicleId();
    }

    public List<VehicleDTO> getAllVehicles()
    {
        List<VehicleDTO> res = vehicleRepository.findAll().stream()
            .map(vehicle -> new VehicleDTO(
                vehicle.getId(),
                vehicle.getVin(),
                vehicle.getLastMsgDate(),
                vehicle.getPnb(),
                vehicle.getVehicleType().getId()
            ))
            .collect(Collectors.toList());
        return res;
    }
        
}
