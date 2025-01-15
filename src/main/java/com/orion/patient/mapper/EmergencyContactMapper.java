package com.orion.patient.mapper;

import com.orion.patient.dto.EmergencyContactDTO;
import com.orion.patient.entity.EmergencyContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {

    @Mapping(source = "patient.id", target = "patientId")
    EmergencyContactDTO toDTO(EmergencyContactEntity emergencyContactEntity);

    @Mapping(source = "patientId", target = "patient.id")
    EmergencyContactEntity toEntity(EmergencyContactDTO emergencyContactDTO);
}
