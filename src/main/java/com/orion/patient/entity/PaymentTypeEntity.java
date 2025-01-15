package com.orion.patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments_types")
public class PaymentTypeEntity {
    @Id
    @Size(max = 255)
    @Column(name = "type", nullable = false)
    private String type;

}