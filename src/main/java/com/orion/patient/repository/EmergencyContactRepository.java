package com.orion.patient.repository;

import com.orion.patient.entity.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
}
