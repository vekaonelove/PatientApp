package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.dto.PatientDto;
import com.orion.patient.service.AppointmentService;
import com.orion.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public Page<PatientDto> getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return patientService.getPatients(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        PatientDto patient = patientService.getPatient(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDTO) {
        PatientDto savedPatient = patientService.save(patientDTO);
        return ResponseEntity.ok(savedPatient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDTO) {
        PatientDto updatedPatient = patientService.update(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsForPatient(@PathVariable Long id) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsForPatient(id);
        return ResponseEntity.ok(appointments);
    }
}
