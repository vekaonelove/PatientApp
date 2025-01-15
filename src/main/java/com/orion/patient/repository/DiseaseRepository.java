package com.orion.patient.repository;

import com.orion.patient.entity.DiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Long> {
}
