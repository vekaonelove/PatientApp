package com.kseniya.patient.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public record Document(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotBlank
        @Column(name = "document_name", nullable = false)
        String documentName,

        @NotBlank
        @Column(name = "document_type", nullable = false)
        String documentType,

        @NotBlank
        @URL
        @Column(name = "file_url", nullable = false)
        String fileUrl,

        @NotNull
        @Column(name = "uploaded_at", nullable = false)
        LocalDateTime uploadedAt
) {
}
