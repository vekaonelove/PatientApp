package com.orion.patient.dto;

public record PaymentDTO(
        Long id,
        Long appointmentId,
        String statusName,
        String paymentType) { }
