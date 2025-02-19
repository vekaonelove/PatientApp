package com.orion.patient.mapper;

import com.orion.patient.dto.DocumentTypeDto;
import com.orion.patient.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {
    DocumentTypeDto toDto(DocumentTypeEntity documentTypeEntity);
    DocumentTypeEntity toEntity(DocumentTypeDto documentTypeDTO);
}
