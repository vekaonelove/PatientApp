package com.orion.patient.mapper;

import com.orion.patient.dto.DiseaseDTO;
import com.orion.patient.entity.DiseaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiseaseMapper {
    DiseaseDTO toDTO(DiseaseEntity diseaseEntity);
    DiseaseEntity toEntity(DiseaseDTO diseaseDTO);
}
