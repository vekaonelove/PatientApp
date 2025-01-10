package com.orion.patient.repository;

import com.orion.patient.dao.PatientDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientDAO, Long> {
}
