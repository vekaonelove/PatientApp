package com.orion.patient.dto;

import java.time.Instant;

public record PatientRecordDTO(
        Long id,
        Long patientId,
        Long diseaseId,
        Instant dateStart,
        Instant dateEnd
) {}
