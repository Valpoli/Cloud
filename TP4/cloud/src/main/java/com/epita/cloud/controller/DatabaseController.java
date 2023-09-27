package com.epita.cloud.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epita.cloud.dto.VehicleTypeDTO;
import com.epita.cloud.dto.CompanyDTO;
import com.epita.cloud.dto.CreateVTDTO;
import com.epita.cloud.model.Company;
import com.epita.cloud.model.VehicleType;
import com.epita.cloud.services.CompanyRepository;
import com.epita.cloud.services.VehicleRepository;
import com.epita.cloud.services.VehicleTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

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
            List<VehicleTypeDTO> res = vehicleTypeRepository.findAll().stream()
            .map(vehicleType -> new VehicleTypeDTO(
                vehicleType.getId(),
                vehicleType.getName(),
                vehicleType.getCapacity(),
                vehicleType.getManufacturer(),
                vehicleType.getCompany()
            ))
            .collect(Collectors.toList());
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
        try {
            String res = companyRepository.findCompanyNameByVin(vin);
            if (res == null || res.length() == 0) {
                return new ResponseEntity<String>("Vin doesn't match any company.", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/NbVehicleType")
    public ResponseEntity<Integer> getNbVehicleType(@RequestBody String vehicleTypeName){
        try {
            Integer res = vehicleTypeRepository.findAllVehicleType(vehicleTypeName).size();
            if (res == 0) {
                return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:8081/putVT?VtID=1&vehicleTypeName=test&passengerNumber=5
    @PutMapping("/putVT")
    public ResponseEntity<String> putVehicleType(@RequestParam Integer VtID, @RequestParam String vehicleTypeName, @RequestParam Integer passengerNumber){
        try {
            Optional<VehicleType> ovt = vehicleTypeRepository.findById(VtID);
            if (! ovt.isPresent()) {
                return new ResponseEntity<String>("Vehicle type to change doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            VehicleType vt = ovt.get();
            vt.setName(vehicleTypeName);
            vt.setCapacity(passengerNumber);
            vehicleTypeRepository.save(vt);
            VehicleTypeDTO res = new VehicleTypeDTO(
                vt.getId(),
                vt.getName(),
                vt.getCapacity(),
                vt.getManufacturer(),
                vt.getCompany());
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // {
    //     "name": "Tramwa",
    //     "capacity": 5,
    //     "manufacturer": "FabricantDuVehicule",
    //     "companyId": 1
    // }
    @PostMapping("/postVT")
    public ResponseEntity<String> postVT(@RequestBody CreateVTDTO createVTDTO)
    {
        try {
            Optional<VehicleType> ovt = vehicleTypeRepository.checkVTExistence(createVTDTO.getName());
            if (ovt.isPresent())
            {
                return new ResponseEntity<String>("Name for this vehicle type already exist.", HttpStatus.BAD_REQUEST);
            }
            Optional<Company> oc = companyRepository.findById(createVTDTO.getCompanyId());
            if (! oc.isPresent())
            {
                return new ResponseEntity<String>("This company doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            VehicleType newVT = new VehicleType(createVTDTO.getName(), createVTDTO.getCapacity(),createVTDTO.getManufacturer(),oc.get());
            newVT = vehicleTypeRepository.save(newVT);
            return ResponseEntity.ok(objectMapper.writeValueAsString(createVTDTO));
        }
         catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allCompany")
    public ResponseEntity<String> getAllCompany(){
        try {
            List<CompanyDTO> res = companyRepository.findAll().stream()
            .map(company -> new CompanyDTO(
                company.getId(),
                company.getName()
            ))
            .collect(Collectors.toList());
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companyByID")
    public ResponseEntity<String> getCompanyByID(@RequestBody Integer companyID){
        try {
            Optional<Company> oc = companyRepository.findById(companyID);
            if (! oc.isPresent()) {
                return new ResponseEntity<String>("This company doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            Company c = oc.get();
            CompanyDTO res = new CompanyDTO(c.getId(), c.getName());
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:8081/putCompany?cID=1&companyName=test
    @PutMapping("/putCompany")
    public ResponseEntity<String> putCompany(@RequestParam Integer cID, @RequestParam String companyName){
        try {
            Optional<Company> oc = companyRepository.findById(cID);
            if (! oc.isPresent()) {
                return new ResponseEntity<String>("Company to change doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            Company c = oc.get();
            c.setName(companyName);
            companyRepository.save(c);
            CompanyDTO res = new CompanyDTO(
                c.getId(),
                c.getName());
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:8081/postCompany?companyName=test
    @PostMapping("/postCompany")
    public ResponseEntity<String> postCompany(@RequestParam String companyName)
    {
        try {
            Optional<Company> oc = companyRepository.checkCompanyExistence(companyName);
            if (oc.isPresent())
            {
                return new ResponseEntity<String>("Name for this company already exist.", HttpStatus.BAD_REQUEST);
            }
            Company newCompany = new Company(companyName);
            newCompany = companyRepository.save(newCompany);
            return ResponseEntity.ok(companyName);
        }
         catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/deleteCompany")
    public ResponseEntity<String> deleteCompany(@RequestParam Integer cID){
        try {
            Optional<Company> oc = companyRepository.findById(cID);
            if (! oc.isPresent()) {
                return new ResponseEntity<String>("Company to delete doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            Company c = oc.get();
            CompanyDTO res = new CompanyDTO(
                c.getId(),
                c.getName());
            companyRepository.deleteCompVehicule(cID);
            companyRepository.deleteCompVT(cID);
            companyRepository.deleteCompany(cID);
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
