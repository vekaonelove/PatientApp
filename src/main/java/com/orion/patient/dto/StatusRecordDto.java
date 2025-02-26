package com.orion.patient.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;

public record StatusRecordDto(
        Long id,
        Long patientId,
        String statusName,

        @PastOrPresent(message = "The date should be in past or present")
        Instant updateDate) { }
