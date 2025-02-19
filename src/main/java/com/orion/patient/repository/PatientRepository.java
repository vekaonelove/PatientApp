package com.orion.patient.repository;

import com.orion.patient.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
    PatientEntity findByEmail(String email);

    Page<PatientEntity> findAll(Pageable pageable);
}
