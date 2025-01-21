package com.orion.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record PatientDTO(
        Long id,

        @Email(message = "The email format is incorrect. Follow: example@gmail.com")
        String email,
        String firstName,
        String lastName,

        @Past(message = "Invalid date. The date should be in present or past")
        Date birthDate,

        @Pattern(regexp = "^(Male|Female|Other)$",
                message = "Gender must be 'Male', 'Female', or 'Other'")
        String gender,

        @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
                message = "Invalid phone number format. Use a valid 10-digit number or include a country code")
        String phoneNumber,

        Long ssn,
        String countryName,
        Long cityId,
        String address,
        Long contactId
){}
