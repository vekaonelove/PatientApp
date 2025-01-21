package com.orion.patient.repository;

import com.orion.patient.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, String> {
    Optional<DocumentTypeEntity> findByType(String type);
}
