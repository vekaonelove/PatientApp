package com.orion.patient.mapper;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "countryName", source = "country.name")
    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "contactId", source = "contact.id")
    PatientDTO toDTO(PatientEntity patientEntity);

    @Mapping(target = "contact.id", source = "contactId")
    @Mapping(target = "country.name", source = "countryName")
    @Mapping(target = "city.id", source = "cityId")
    PatientEntity toEntity(PatientDTO patientDTO);
}
