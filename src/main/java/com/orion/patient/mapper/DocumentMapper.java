package com.orion.patient.mapper;

import com.orion.patient.dto.DocumentDTO;
import com.orion.patient.entity.DocumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDTO toDTO(DocumentEntity documentEntity);
    DocumentEntity toEntity(DocumentDTO documentDTO);
}
