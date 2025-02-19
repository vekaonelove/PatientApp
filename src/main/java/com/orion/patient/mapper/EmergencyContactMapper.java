package com.orion.patient.mapper;

import com.orion.patient.dto.EmergencyContactDto;
import com.orion.patient.entity.EmergencyContactEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {
    EmergencyContactDto toDto(EmergencyContactEntity emergencyContactEntity);
    EmergencyContactEntity toEntity(EmergencyContactDto emergencyContactDTO);
}
