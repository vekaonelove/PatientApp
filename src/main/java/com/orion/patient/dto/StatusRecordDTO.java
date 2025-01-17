package com.orion.patient.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;

public record StatusRecordDTO(
        Integer id,
        Long patientId,
        String statusName,

        @PastOrPresent
        Instant updateDate) { }
