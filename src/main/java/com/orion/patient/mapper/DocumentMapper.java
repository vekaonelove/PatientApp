package com.orion.patient.mapper;

import com.orion.patient.dto.DocumentDto;
import com.orion.patient.entity.DocumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDto toDto(DocumentEntity documentEntity);
    DocumentEntity toEntity(DocumentDto documentDTO);
}
