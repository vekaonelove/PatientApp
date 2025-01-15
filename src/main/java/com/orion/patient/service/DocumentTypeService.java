package com.orion.patient.service;

import com.orion.patient.dto.DocumentTypeDTO;
import com.orion.patient.entity.DocumentTypeEntity;
import com.orion.patient.mapper.DocumentTypeMapper;
import com.orion.patient.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    public List<DocumentTypeDTO> findAll() {
        List<DocumentTypeEntity> documentTypes = documentTypeRepository.findAll();
        return documentTypes.stream()
                .map(documentTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DocumentTypeDTO> findByName(String name) {
        Optional<DocumentTypeEntity> documentTypeEntity = documentTypeRepository.findByType(name);
        return documentTypeEntity.map(documentTypeMapper::toDTO);
    }

    public DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO) {
        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentTypeDTO);
        DocumentTypeEntity savedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDTO(savedDocumentType);
    }

    public DocumentTypeDTO update(String type, DocumentTypeDTO documentTypeDTO) {
        if (!documentTypeRepository.existsById(type)) {
            return null;
        }
        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentTypeDTO);
        documentTypeEntity.setType(type);
        DocumentTypeEntity updatedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDTO(updatedDocumentType);
    }

    public void delete(String name) {
        documentTypeRepository.deleteById(name);
    }
}
