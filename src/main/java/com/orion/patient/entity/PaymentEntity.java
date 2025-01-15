package com.orion.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;

    @Size(max = 255)
    @NotNull
    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Size(max = 255)
    @Column(name = "payment_type")
    private String paymentType;

}