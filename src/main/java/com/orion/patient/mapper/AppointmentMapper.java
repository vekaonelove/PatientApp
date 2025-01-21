package com.orion.patient.mapper;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "patient.id", target = "patientId")
    AppointmentDTO toDTO(AppointmentEntity entity);

    @Mapping(source = "patientId", target = "patient.id")
    AppointmentEntity toEntity(AppointmentDTO dto);
}
