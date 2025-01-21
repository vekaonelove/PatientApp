package com.orion.patient.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;

public record PatientRecordDTO(
        Long id,
        Long patientId,
        Long diseaseId,

        @PastOrPresent
        Instant dateStart,
        Instant dateEnd
) {}
