package com.epita.cloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epita.cloud.dto.StudentDTO;
import com.epita.cloud.dto.VehicleTypeDTO;
import com.epita.cloud.model.VehicleType;
import com.epita.cloud.services.CompanyRepository;
import com.epita.cloud.services.VehicleRepository;
import com.epita.cloud.services.VehicleTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DatabaseController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    public VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/allVehicleTypes")
    public ResponseEntity<String> getAllVehicleTypes(){
        try {
            List<VehicleTypeDTO> res = new ArrayList<VehicleTypeDTO>();
            Stream<VehicleType> vehicleTypes =  vehicleTypeRepository.findAll().stream();
            vehicleTypes.forEach(element -> {res.add(new VehicleTypeDTO(element.getId(),element.getName(),
            element.getCapacity(), element.getManufacturer(), element.getCompany()));});
            System.out.println(res);
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/CompanyByVin")
    public ResponseEntity<String> getCompanyByVin(@RequestBody String vin){
        String res = companyRepository.findCompanyNameByVin(vin);
        if (res == null || res.length()==0)
            return new ResponseEntity<String>("Vin do not match to any company", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(res);
    }

    
}