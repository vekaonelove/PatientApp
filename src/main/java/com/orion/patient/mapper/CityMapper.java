package com.orion.patient.mapper;

import com.orion.patient.dto.CityDTO;
import com.orion.patient.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDTO toDTO(CityEntity cityEntity);
    CityEntity toEntity(CityDTO cityDTO);
}
