package com.orion.patient.repository;

import com.orion.patient.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
    PatientEntity findByEmail(String email);
}
