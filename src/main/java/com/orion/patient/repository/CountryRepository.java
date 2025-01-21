package com.orion.patient.repository;

import com.orion.patient.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
    CountryEntity findByName(String name);
}
