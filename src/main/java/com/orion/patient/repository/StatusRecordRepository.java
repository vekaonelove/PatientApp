package com.orion.patient.repository;

import com.orion.patient.entity.StatusRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRecordRepository extends JpaRepository<StatusRecordEntity, Long> {
}
