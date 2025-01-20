package com.orion.patient.controller;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class AuthorizationController {

    private final PatientService patientService;

    public AuthorizationController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody PatientDTO patientDTO) {

        if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization token");
        }

        String token = authorizationHeader.substring(7); //without Bearer

        if (patientService.patientExists(patientDTO)) {
            return ResponseEntity.badRequest().body("Patient already exists");
        }

        patientService.save(patientDTO);
        return ResponseEntity.ok("Patient registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getPatientProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("id") Long id) {

        if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization token");
        }

        Optional<PatientDTO> patientDTO = patientService.findById(id);
        if (patientDTO.isEmpty()) {
            return ResponseEntity.status(404).body("Patient not found");
        }

        return ResponseEntity.ok(patientDTO.get());
    }
}
