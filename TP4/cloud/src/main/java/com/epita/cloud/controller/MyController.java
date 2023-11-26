package com.epita.cloud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epita.cloud.StudentDTO;

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
}


