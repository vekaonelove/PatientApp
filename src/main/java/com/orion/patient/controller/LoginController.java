package com.orion.patient.controller;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody PatientDTO patientDTO) {

        if (token == null || token.isBlank()) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization token");
        }

        if (patientService.patientExists(patientDTO)) {
            return ResponseEntity.badRequest().body("Patient already exists");
        }

        patientService.save(patientDTO);
        return ResponseEntity.ok("Patient registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<Optional<PatientDTO>> getPatientProfile(
            @RequestHeader("Authorization") String token,
            @RequestParam("id") Long id) {

        // Token validation
        if (token == null || token.isBlank()) {
            return ResponseEntity.status(401).body(null);
        }

        // Retrieve patient profile by ID
        Optional<PatientDTO> patientDTO = patientService.findById(id);
        return ResponseEntity.ok(patientDTO);
    }
}
