package com.orion.patient.mapper;

import com.orion.patient.dto.PatientStatusDto;
import com.orion.patient.entity.PatientStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientStatusMapper {
    PatientStatusDto toDto(PatientStatusEntity patientStatusEntity);
    PatientStatusEntity toEntity(PatientStatusDto patientStatusDTO);
}
