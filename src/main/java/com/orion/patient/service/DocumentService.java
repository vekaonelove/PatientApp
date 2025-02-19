package com.orion.patient.service;

import com.orion.patient.dto.DocumentDto;
import com.orion.patient.entity.DocumentEntity;
import com.orion.patient.mapper.DocumentMapper;
import com.orion.patient.repository.DocumentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDto> findAll() {
        List<DocumentEntity> documents = documentRepository.findAll();
        return documents.stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DocumentDto> findById(Long id) {
        Optional<DocumentEntity> documentEntity = documentRepository.findById(id);
        return documentEntity.map(documentMapper::toDto);
    }

    public DocumentDto save(DocumentDto documentDTO) {
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO);
        DocumentEntity savedDocument = documentRepository.save(documentEntity);
        return documentMapper.toDto(savedDocument);
    }

    public DocumentDto update(Long id, @Valid DocumentDto documentDTO) {
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO);
        documentEntity.setId(id);
        DocumentEntity updatedDocument = documentRepository.save(documentEntity);
        return documentMapper.toDto(updatedDocument);
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
