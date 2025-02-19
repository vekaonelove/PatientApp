package com.orion.patient.dto;

public record PaymentDto(
        Long id,
        Long appointmentId,
        String statusName,
        String paymentType) { }
