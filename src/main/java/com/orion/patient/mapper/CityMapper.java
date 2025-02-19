package com.orion.patient.mapper;

import com.orion.patient.dto.CityDto;
import com.orion.patient.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(target = "countryName", source = "country.name")
    CityDto toDto(CityEntity cityEntity);
    @Mapping(target = "country.name", source = "countryName")
    CityEntity toEntity(CityDto cityDTO);
}
