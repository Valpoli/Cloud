package com.epita.cloud;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class MyController {
    @CrossOrigin
    @PostMapping("/hello/name")
    public String sayHello(@RequestBody @Valid StudentDTO student){
    return "Hello " + student.getName();
    }
}
