package com.epita.cloud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epita.cloud.StudentDTO;
import com.epita.cloud.dto.TelematicsDTO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import jakarta.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    @PostMapping("/hello")
    public String sayHello(@RequestBody StudentDTO student){
        logger.info("test");
    return "Hello " + student.getName();
    }

    @PostMapping("/telematics")
    public void logTelematicsData(@RequestBody TelematicsDTO telematicsData) {
        String unixSecondsString = telematicsData.getLocalTime();
        long unixSeconds = Long.parseLong(unixSecondsString);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixSeconds, 0, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy:MM:dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        logger.info("Received telematics data: VIN={}, date={}, passengers={}",
                telematicsData.getVehicleId(), formattedDateTime, telematicsData.getPassengerNumber());
    }

}


