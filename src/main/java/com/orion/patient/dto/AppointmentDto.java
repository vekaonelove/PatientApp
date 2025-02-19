package com.orion.patient.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long id,
        Long patientId,
        Long doctorId,
        Long clinicId,
        Long diseaseId,
        Long documentId,

        @FutureOrPresent(message = "Date should be in present or future")
        LocalDateTime appointmentTime
) {
}
