package com.orion.patient.mapper;

import com.orion.patient.dto.DiseaseDto;
import com.orion.patient.entity.DiseaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiseaseMapper {
    DiseaseDto toDto(DiseaseEntity diseaseEntity);
    DiseaseEntity toEntity(DiseaseDto diseaseDTO);
}
