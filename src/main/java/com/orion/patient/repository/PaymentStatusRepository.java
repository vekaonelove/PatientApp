package com.orion.patient.repository;

import com.orion.patient.entity.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, String> {
}
