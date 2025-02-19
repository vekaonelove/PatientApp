package com.orion.patient.controller;

import com.orion.patient.dto.PatientDto;
import com.orion.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RegisterController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(
            @Valid @RequestBody PatientDto patientDTO) {

        if (patientService.patientExists(patientDTO)) {
            return ResponseEntity.badRequest().body("Patient already exists");
        }

        PatientDto savedPatient = patientService.save(patientDTO);

        return ResponseEntity.status(201).body("Patient registered successfully with ID: " + savedPatient.id());
    }

    @GetMapping("/patient-profile")
    public ResponseEntity<?> getPatientProfile(
            @RequestParam("id") Long id) {

        PatientDto patientDTO = patientService.getPatient(id);
        return ResponseEntity.ok(patientDTO);
    }
}
