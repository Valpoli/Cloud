package com.epita.cloud.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epita.cloud.dto.VehicleTypeDTO;
import com.epita.cloud.dto.CompanyDTO;
import com.epita.cloud.dto.CreateCompDTO;
import com.epita.cloud.dto.CreateVTDTO;
import com.epita.cloud.dto.PutVehicleTypeDTO;
import com.epita.cloud.dto.TelematicsDTO;
import com.epita.cloud.dto.VehicleDTO;
import com.epita.cloud.model.Company;
import com.epita.cloud.model.VehicleType;
import com.epita.cloud.service.CompanyService;
import com.epita.cloud.service.VehicleService;
import com.epita.cloud.service.VehicleTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5177", "http://localhost/"})

@RestController
public class DatabaseController {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public VehicleTypeService vehicleTypeService;
    @Autowired
    public CompanyService companyService;
    @Autowired
    public VehicleService vehicleService;

    // This method retrieves and returns all vehicle types present in the database.
    @GetMapping("/allVehicleTypes")
    public ResponseEntity<String> getAllVehicleTypes(){
        try {
            List<VehicleTypeDTO> res = vehicleTypeService.getAllVehicleTypes();
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method searches for a company based on a given vehicle's VIN and returns the name of the company associated with that VIN.
    @GetMapping("/CompanyByVin")
    public ResponseEntity<String> getCompanyByVin(@RequestBody String vin){
        try {
            String res = companyService.findCompanyNameByVin(vin);
            if (res == null || res.length() == 0) {
                return new ResponseEntity<String>("Vin doesn't match any company.", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method counts the number of vehicle types that match a given vehicle type name and returns this count.
    @GetMapping("/NbVehicleType")
    public ResponseEntity<?> getNbVehicleType(@RequestParam String vehicleTypeName){
        try {
            Integer res = vehicleTypeService.getNbVehicleType(vehicleTypeName);
            if (res == 0) {
                return new ResponseEntity<String>("Bad vehicle type name.", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method updates the details of an existing vehicle type based on the vehicle type ID, vehicle type name, and passenger number.
    @Transactional
    @PutMapping("/putVT")
    public ResponseEntity<String> putVehicleType(@RequestBody PutVehicleTypeDTO putvehicleTypeDTO){
        try {
            if (putvehicleTypeDTO.getVtID() == null || putvehicleTypeDTO.getVehicleTypeName() == null)
            {
                return new ResponseEntity<String>("Invalid body, missing argument.", HttpStatus.BAD_REQUEST);
            }
            Optional<VehicleType> ovt = vehicleTypeService.findvehicleByID(putvehicleTypeDTO.getVtID());
            if (! ovt.isPresent()) {
                return new ResponseEntity<String>("Vehicle type to change doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            VehicleType vt = ovt.get();
            vt.setName(putvehicleTypeDTO.getVehicleTypeName());
            vehicleTypeService.save(vt);
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

    // This method creates a new vehicle type based on the provided information such as name, capacity, manufacturer, and company ID.
    @Transactional
    @PostMapping("/postVT")
    public ResponseEntity<String> postVT(@RequestBody CreateVTDTO createVTDTO)
    {
        try {
            System.out.println(createVTDTO.getName());
            System.out.println(createVTDTO.getCapacity());
            System.out.println(createVTDTO.getManufacturer());
            System.out.println(createVTDTO.getCompanyId());
            if (createVTDTO.getName() == null || createVTDTO.getCapacity() == null || createVTDTO.getCompanyId() == null)
            {
                return new ResponseEntity<String>("Invalid body, missing argument.", HttpStatus.BAD_REQUEST);
            }
            if (createVTDTO.getManufacturer() == null)
            {
                createVTDTO.setManufacturer("");
            }
            Optional<VehicleType> ovt = vehicleTypeService.checkVTExistence(createVTDTO.getName());
            if (ovt.isPresent())
            {
                return new ResponseEntity<String>("Name for this vehicle type already exist.", HttpStatus.BAD_REQUEST);
            }
            Optional<Company> oc = companyService.findCompanyByID(createVTDTO.getCompanyId());
            if (! oc.isPresent())
            {
                return new ResponseEntity<String>("This company doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            VehicleType newVT = new VehicleType(createVTDTO.getName(), createVTDTO.getCapacity(),createVTDTO.getManufacturer(),oc.get());
            newVT = vehicleTypeService.save(newVT);
            return ResponseEntity.ok(objectMapper.writeValueAsString(createVTDTO));
        }
         catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method retrieves and returns all companies present in the database.
    @GetMapping("/allCompany")
    public ResponseEntity<String> getAllCompany(){
        try {
            List<CompanyDTO> res = companyService.getAllCompany();
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method retrieves a company by its ID and returns its details.
    @GetMapping("/companyByID")
    public ResponseEntity<String> getCompanyByID(@RequestBody Integer companyID){
        try {
            Optional<Company> oc = companyService.findCompanyByID(companyID);
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

    // This method updates the name of an existing company based on its ID.
    @Transactional
    @PutMapping("/putCompany")
    public ResponseEntity<String> putCompany(@RequestBody CompanyDTO companyDTO){
        try {
            System.out.println(companyDTO.getId());
            System.out.println(companyDTO.getName());
            Optional<Company> oc = companyService.findCompanyByID(companyDTO.getId());
            if (! oc.isPresent()) {
                return new ResponseEntity<String>("Company to change doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            Company c = oc.get();
            c.setName(companyDTO.getName());
            companyService.save(c);
            CompanyDTO res = new CompanyDTO(
                c.getId(),
                c.getName());
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method creates a new company with the provided name.
    @Transactional
    @PostMapping("/postCompany")
    public ResponseEntity<String> postCompany(@RequestBody CreateCompDTO createCompDTO)
    {
        try {
            Optional<Company> oc = companyService.checkCompanyExistence(createCompDTO.getName());
            if (oc.isPresent())
            {
                return new ResponseEntity<String>("Name for this company already exist.", HttpStatus.BAD_REQUEST);
            }
            Company newCompany = new Company(createCompDTO.getName());
            newCompany = companyService.save(newCompany);
            return ResponseEntity.ok(createCompDTO.getName());
        }
         catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method deletes a company and all associated entities (vehicle types and vehicles) based on the company's ID.
    @Transactional
    @DeleteMapping("/deleteCompany")
    public ResponseEntity<String> deleteCompany(@RequestParam Integer cID){
        try {
            Optional<Company> oc = companyService.findCompanyByID(cID);
            if (! oc.isPresent()) {
                return new ResponseEntity<String>("Company to delete doesn't exist.", HttpStatus.BAD_REQUEST);
            }
            Company c = oc.get();
            CompanyDTO res = new CompanyDTO(
                c.getId(),
                c.getName());
            companyService.deleteCompvehicle(cID);
            companyService.deleteCompVT(cID);
            companyService.deleteCompany(cID);
            return ResponseEntity.ok(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method change the passenger number and the timestamp of vehicle.
    @Transactional
    @PostMapping("/telematics")
    public ResponseEntity<String> logTelematicsData(@RequestBody TelematicsDTO telematicsData) {
        try {
            String unixSecondsString = telematicsData.getLocalTime();
            long unixSeconds = Long.parseLong(unixSecondsString);
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixSeconds, 0, ZoneOffset.UTC);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy:MM:dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            logger.info("Received telematics data: VIN={}, date={}, passengers={}",
                    telematicsData.getVehicleId(), formattedDateTime, telematicsData.getPassengerNumber());
            String vtChanged = vehicleService.modifyVehicleInfos(telematicsData);
            return ResponseEntity.ok(vtChanged);
         }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This method retrieves and returns all vehicle types present in the database.
    @GetMapping("/allVehicles")
    public ResponseEntity<String> getAllVehicles(){
        try {
            List<VehicleDTO> res = vehicleService.getAllVehicles();
            String resJson = objectMapper.writeValueAsString(res);
            return ResponseEntity.ok(resJson);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
