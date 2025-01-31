package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.dto.PatientDTO;
import com.orion.patient.service.AppointmentService;
import com.orion.patient.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Validated
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatient = patientService.save(patientDTO);
        return ResponseEntity.ok(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientService.update(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForPatient(@PathVariable Long id) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsForPatient(id);
        return ResponseEntity.ok(appointments);
    }
}
