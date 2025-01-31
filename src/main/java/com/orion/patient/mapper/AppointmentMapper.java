package com.orion.patient.mapper;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "diseaseId", source = "disease.id")
    @Mapping(source = "patient.id", target = "patientId")
    AppointmentDTO toDTO(AppointmentEntity entity);

    @Mapping(target = "document.id", source = "documentId")
    @Mapping(target = "disease.id", source = "diseaseId")
    @Mapping(source = "patientId", target = "patient.id")
    AppointmentEntity toEntity(AppointmentDTO dto);
}
