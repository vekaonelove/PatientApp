package com.orion.patient.mapper;

import com.orion.patient.dto.PatientStatusDTO;
import com.orion.patient.entity.PatientStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientStatusMapper {
    PatientStatusDTO toDTO(PatientStatusEntity patientStatusEntity);
    PatientStatusEntity toEntity(PatientStatusDTO patientStatusDTO);
}
