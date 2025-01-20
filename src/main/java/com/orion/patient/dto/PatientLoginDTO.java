package com.orion.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record PatientLoginDTO(
        Long id,

        @Email
        String email,
        @Pattern(message = "Password must be at least 8 characters long and contain at least one number, one uppercase, " +
                "one lowercase and one special character",
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
        String password
) {}
