package com.orion.patient.repository;

import com.orion.patient.entity.PatientStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientStatusRepository extends JpaRepository<PatientStatusEntity, String> {
}
