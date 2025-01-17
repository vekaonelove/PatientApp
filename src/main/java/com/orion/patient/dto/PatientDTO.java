package com.orion.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import java.util.Date;

public record PatientDTO(
        Long id,

        @Email(message = "The email format is incorrect. Follow: example@gmail.com")
        String email,
        String firstName,
        String lastName,

        @Past(message = "Choose the date in the past")
        Date birthDate,
        String gender,
        String phoneNumber,
        Long ssn,
        String countryName,
        Long cityId,
        String address,
        Long contactId
){}
