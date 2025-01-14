package com.orion.patient.dto;

public record EmergencyContactDTO(
        Long id,
        String name,
        String phoneNumber,
        String additionalContact,
        Long patientId,
        String relation
) {}
