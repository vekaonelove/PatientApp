package com.orion.patient.mapper;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "diseaseId", source = "disease.id")
    @Mapping(source = "patient.id", target = "patientId")
    AppointmentDto toDto(AppointmentEntity entity);

    @Mapping(target = "document.id", source = "documentId")
    @Mapping(target = "disease.id", source = "diseaseId")
    @Mapping(source = "patientId", target = "patient.id")
    AppointmentEntity toEntity(AppointmentDto dto);
}
