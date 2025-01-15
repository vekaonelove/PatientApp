package com.orion.patient.dto;

import java.time.Instant;

public record AppointmentDTO(
        Long id,
        Long patientId,
        Long doctorId,
        Long clinicId,
        Instant appointmentDate
) {
}
