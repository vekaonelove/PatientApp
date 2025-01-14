package com.orion.patient.mapper;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO(PatientEntity patientEntity);
    PatientEntity toEntity(PatientDTO patientDTO);
}
