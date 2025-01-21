package com.orion.patient.mapper;

import com.orion.patient.dto.EmergencyContactDTO;
import com.orion.patient.entity.EmergencyContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {
    EmergencyContactDTO toDTO(EmergencyContactEntity emergencyContactEntity);
    EmergencyContactEntity toEntity(EmergencyContactDTO emergencyContactDTO);
}
