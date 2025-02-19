package com.orion.patient.dto;

public record EmergencyContactDto(
        Long id,
        String name,
        String phoneNumber,
        String additionalContact,
        String relation
) {}
