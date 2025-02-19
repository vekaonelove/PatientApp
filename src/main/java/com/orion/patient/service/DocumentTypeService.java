package com.orion.patient.service;

import com.orion.patient.dto.DocumentTypeDto;
import com.orion.patient.entity.DocumentTypeEntity;
import com.orion.patient.mapper.DocumentTypeMapper;
import com.orion.patient.repository.DocumentTypeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    public List<DocumentTypeDto> findAll() {
        List<DocumentTypeEntity> documentTypes = documentTypeRepository.findAll();
        return documentTypes.stream()
                .map(documentTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DocumentTypeDto> findByName(String name) {
        Optional<DocumentTypeEntity> documentTypeEntity = documentTypeRepository.findByType(name);
        return documentTypeEntity.map(documentTypeMapper::toDto);
    }

    public DocumentTypeDto save(DocumentTypeDto documentTypeDTO) {
        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentTypeDTO);
        DocumentTypeEntity savedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDto(savedDocumentType);
    }

    public DocumentTypeDto update(String type, @Valid DocumentTypeDto documentTypeDTO) {
        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentTypeDTO);
        documentTypeEntity.setType(type);
        DocumentTypeEntity updatedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDto(updatedDocumentType);
    }

    public void delete(String name) {
        documentTypeRepository.deleteById(name);
    }
}
