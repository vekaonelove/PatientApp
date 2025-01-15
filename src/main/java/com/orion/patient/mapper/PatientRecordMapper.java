package com.orion.patient.mapper;

import com.orion.patient.dto.PatientRecordDTO;
import com.orion.patient.entity.PatientRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientRecordMapper {

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "disease.id", target = "diseaseId")
    PatientRecordDTO toDTO(PatientRecordEntity patientRecordEntity);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "diseaseId", target = "disease.id")
    PatientRecordEntity toEntity(PatientRecordDTO patientRecordDTO);
}
