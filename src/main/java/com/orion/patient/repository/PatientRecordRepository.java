package com.orion.patient.repository;

import com.orion.patient.entity.PatientRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecordEntity, Long> {
}
