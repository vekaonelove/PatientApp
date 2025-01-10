package com.orion.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientApplication {

    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }
}
