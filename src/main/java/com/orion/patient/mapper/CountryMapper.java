package com.orion.patient.mapper;

import com.orion.patient.dto.CountryDto;
import com.orion.patient.entity.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(CountryEntity countryEntity);
    CountryEntity toEntity(CountryDto countryDTO);
}
