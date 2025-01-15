package com.orion.patient.service;

import com.orion.patient.dto.DocumentDTO;
import com.orion.patient.entity.DocumentEntity;
import com.orion.patient.mapper.DocumentMapper;
import com.orion.patient.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDTO> findAll() {
        List<DocumentEntity> documents = documentRepository.findAll();
        return documents.stream()
                .map(documentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DocumentDTO> findById(Long id) {
        Optional<DocumentEntity> documentEntity = documentRepository.findById(id);
        return documentEntity.map(documentMapper::toDTO);
    }

    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO);
        DocumentEntity savedDocument = documentRepository.save(documentEntity);
        return documentMapper.toDTO(savedDocument);
    }

    public DocumentDTO update(Long id, DocumentDTO documentDTO) {
        if (!documentRepository.existsById(id)) {
            return null;
        }
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO);
        documentEntity.setId(id);
        DocumentEntity updatedDocument = documentRepository.save(documentEntity);
        return documentMapper.toDTO(updatedDocument);
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
