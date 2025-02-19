package com.orion.patient.service;

import com.orion.patient.dto.DocumentTypeDto;
import com.orion.patient.entity.DocumentTypeEntity;
import com.orion.patient.mapper.DocumentTypeMapper;
import com.orion.patient.repository.DocumentTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentTypeServiceTests {

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private DocumentTypeMapper documentTypeMapper;

    @InjectMocks
    private DocumentTypeService documentTypeService;

    private DocumentTypeEntity documentTypeEntity;
    private DocumentTypeDto documentTypeDto;

    @BeforeEach
    void setUp() {
        documentTypeEntity = new DocumentTypeEntity();
        documentTypeEntity.setType("Passport");

        documentTypeDto = new DocumentTypeDto("Passport");
    }

    @Test
    void findAll_ShouldReturnListOfDocumentTypeDtos() {
        when(documentTypeRepository.findAll()).thenReturn(List.of(documentTypeEntity));
        when(documentTypeMapper.toDto(documentTypeEntity)).thenReturn(documentTypeDto);

        List<DocumentTypeDto> result = documentTypeService.findAll();

        assertEquals(1, result.size());
        assertEquals("Passport", result.get(0).type());
    }

    @Test
    void findByName_ShouldReturnDocumentTypeDto_WhenDocumentTypeExists() {
        when(documentTypeRepository.findByType("Passport")).thenReturn(Optional.of(documentTypeEntity));
        when(documentTypeMapper.toDto(documentTypeEntity)).thenReturn(documentTypeDto);

        Optional<DocumentTypeDto> result = documentTypeService.findByName("Passport");

        assertTrue(result.isPresent());
        assertEquals("Passport", result.get().type());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenDocumentTypeDoesNotExist() {
        when(documentTypeRepository.findByType("Passport")).thenReturn(Optional.empty());

        Optional<DocumentTypeDto> result = documentTypeService.findByName("Passport");

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedDocumentTypeDto() {
        when(documentTypeMapper.toEntity(documentTypeDto)).thenReturn(documentTypeEntity);
        when(documentTypeRepository.save(documentTypeEntity)).thenReturn(documentTypeEntity);
        when(documentTypeMapper.toDto(documentTypeEntity)).thenReturn(documentTypeDto);

        DocumentTypeDto result = documentTypeService.save(documentTypeDto);

        assertEquals("Passport", result.type());
    }

    @Test
    void update_ShouldReturnUpdatedDocumentTypeDto() {
        when(documentTypeMapper.toEntity(documentTypeDto)).thenReturn(documentTypeEntity);
        when(documentTypeRepository.save(documentTypeEntity)).thenReturn(documentTypeEntity);
        when(documentTypeMapper.toDto(documentTypeEntity)).thenReturn(documentTypeDto);

        DocumentTypeDto result = documentTypeService.update("Passport", documentTypeDto);

        assertEquals("Passport", result.type());
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(documentTypeRepository).deleteById("Passport");

        documentTypeService.delete("Passport");

        verify(documentTypeRepository, times(1)).deleteById("Passport");
    }
}
