package com.orion.patient.dto;

import java.util.Date;

public record PatientDTO(
        Long id,
        String firstName,
        String lastName,
        Date birthDate,
        String gender,
        String phoneNumber,
        Long ssn,
        String countryName,
        Long cityId,
        String address,
        Long contactId
) {}
