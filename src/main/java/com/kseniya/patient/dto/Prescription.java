package com.kseniya.patient.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public record Prescription(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotNull
        @Column(name = "patient_id", nullable = false)
        Long patientId,

        @NotNull
        @Column(name = "doctor_id", nullable = false)
        Long doctorId,

        @NotNull
        @Column(name = "clinic_id", nullable = false)
        String clinicId,

        @NotNull
        @Column(name = "prescription_date", nullable = false)
        LocalDate prescriptionDate,

        @Column(name = "description")
        String description
) {
}
