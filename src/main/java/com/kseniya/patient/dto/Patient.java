package com.kseniya.patient.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.processing.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
public record Patient(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotBlank
        @Column(name = "first_name", nullable = false)
        String firstName,

        @NotBlank
        @Column(name = "last_name", nullable = false)
        String lastName,

        @NotNull
        @Column(name = "birth_date", nullable = false)
        LocalDate birthDate,

        @NotBlank
        @Column(name = "gender", nullable = false)
        String gender,

        @NotBlank
        @Column(name = "phone_number", nullable = false, unique = true)
        String phoneNumber,

        @NotBlank
        @Column(name = "ssn", nullable = false, unique = true)
        String ssn,

        @NotBlank
        @Column(name = "city", nullable = false)
        String city,

        @Size(max = 255)
        @Column(name = "address")
        String address
) {}
