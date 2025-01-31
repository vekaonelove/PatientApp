package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointmentCreate")
    public ResponseEntity<String> createAppointment(
            @Valid @RequestBody AppointmentDTO appointmentDTO
    ) {
        boolean isAvailable = appointmentService.isDoctorAvailable(
                appointmentDTO.doctorId(),
                appointmentDTO.clinicId(),
                appointmentDTO.appointmentTime()
        );

        if (!isAvailable) {
            return ResponseEntity.badRequest().body("Doctor is not available in the specified clinic at this time.");
        }

        appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok("Appointment created successfully.");
    }

    @GetMapping("/patient/appointments/{patientId}")
    public ResponseEntity<?> getAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForPatient(patientId));
    }

    @PatchMapping("/{appointmentId}")
    public ResponseEntity<String> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentDTO appointmentDTO
    ) {
        boolean isAvailable = appointmentService.isDoctorAvailable(
                appointmentDTO.doctorId(),
                appointmentDTO.clinicId(),
                appointmentDTO.appointmentTime()
        );

        if (!isAvailable) {
            return ResponseEntity.badRequest().body("Doctor is not available in the specified clinic at this time.");
        }

        AppointmentDTO updated = appointmentService.update(appointmentId, appointmentDTO);

        if (updated != null) {
            return ResponseEntity.ok("Appointment updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update the appointment.");
        }
    }

}
