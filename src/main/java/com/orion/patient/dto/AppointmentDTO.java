package com.orion.patient.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.Instant;

public record AppointmentDTO(
        Long id,
        Long patientId,
        Long doctorId,
        Long clinicId,

        @FutureOrPresent(message = "Date should be in present or future")
        Instant appointmentDate
) {
}
