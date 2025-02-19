package com.orion.patient.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;

public record PatientRecordDto(
        Long id,
        Long patientId,
        Long diseaseId,

        @PastOrPresent(message = "The date should be in past or present")
        Instant dateStart,
        Instant dateEnd
) {}
