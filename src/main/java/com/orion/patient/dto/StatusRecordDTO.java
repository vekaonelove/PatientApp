package com.orion.patient.dto;

import java.time.Instant;

public record StatusRecordDTO(
        Integer id, Long patientId, String statusName, Instant updateDate) { }
