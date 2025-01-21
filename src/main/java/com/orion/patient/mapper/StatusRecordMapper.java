package com.orion.patient.mapper;

import com.orion.patient.dto.StatusRecordDTO;
import com.orion.patient.entity.StatusRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusRecordMapper {

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "statusName.status", target = "statusName")
    StatusRecordDTO toDTO(StatusRecordEntity statusRecordEntity);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "statusName", target = "statusName.status")
    StatusRecordEntity toEntity(StatusRecordDTO statusRecordDTO);
}
