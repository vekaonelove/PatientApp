package com.orion.patient.mapper;

import com.orion.patient.dto.CountryDTO;
import com.orion.patient.entity.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDTO(CountryEntity countryEntity);
    CountryEntity toEntity(CountryDTO countryDTO);
}
