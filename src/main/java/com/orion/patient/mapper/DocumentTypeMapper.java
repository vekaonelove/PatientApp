package com.orion.patient.mapper;

import com.orion.patient.dto.DocumentTypeDTO;
import com.orion.patient.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {
    DocumentTypeDTO toDTO(DocumentTypeEntity documentTypeEntity);
    DocumentTypeEntity toEntity(DocumentTypeDTO documentTypeDTO);
}
