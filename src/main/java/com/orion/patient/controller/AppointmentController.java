package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(
            @Valid @RequestBody AppointmentDto appointmentDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessage.toString().trim());
        }

        boolean isAvailable = appointmentService.isDoctorNotAvailable(
                appointmentDTO.doctorId(),
                appointmentDTO.clinicId(),
                appointmentDTO.appointmentTime()
        );

        if (!isAvailable) {
            return ResponseEntity.badRequest().body("Doctor is not available in the specified clinic at this time.");
        }

        appointmentService.createAppointment(appointmentDTO);

        return ResponseEntity.status(201).body("Appointment created successfully.");
    }

}
