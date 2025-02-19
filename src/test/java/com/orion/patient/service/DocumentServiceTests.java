package com.orion.patient.service;

import com.orion.patient.dto.DocumentDto;
import com.orion.patient.entity.DocumentEntity;
import com.orion.patient.mapper.DocumentMapper;
import com.orion.patient.repository.DocumentRepository;
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
class DocumentServiceTests {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentService documentService;

    private DocumentEntity documentEntity;
    private DocumentDto documentDto;

    @BeforeEach
    void setUp() {
        byte[] content = "sample content".getBytes();
        documentEntity = new DocumentEntity();
        documentEntity.setId(1L);
        documentEntity.setContent(content);

        documentDto = new DocumentDto(1L, null, content);
    }

    @Test
    void findAll_ShouldReturnListOfDocumentDtos() {
        when(documentRepository.findAll()).thenReturn(List.of(documentEntity));
        when(documentMapper.toDto(documentEntity)).thenReturn(documentDto);

        List<DocumentDto> result = documentService.findAll();

        assertEquals(1, result.size());
        assertArrayEquals("sample content".getBytes(), result.get(0).content());
    }

    @Test
    void findById_ShouldReturnDocumentDto_WhenDocumentExists() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(documentEntity));
        when(documentMapper.toDto(documentEntity)).thenReturn(documentDto);

        Optional<DocumentDto> result = documentService.findById(1L);

        assertTrue(result.isPresent());
        assertArrayEquals("sample content".getBytes(), result.get().content());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenDocumentDoesNotExist() {
        when(documentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<DocumentDto> result = documentService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedDocumentDto() {
        when(documentMapper.toEntity(documentDto)).thenReturn(documentEntity);
        when(documentRepository.save(documentEntity)).thenReturn(documentEntity);
        when(documentMapper.toDto(documentEntity)).thenReturn(documentDto);

        DocumentDto result = documentService.save(documentDto);

        assertArrayEquals("sample content".getBytes(), result.content());
    }

    @Test
    void update_ShouldReturnUpdatedDocumentDto() {
        when(documentMapper.toEntity(documentDto)).thenReturn(documentEntity);
        when(documentRepository.save(documentEntity)).thenReturn(documentEntity);
        when(documentMapper.toDto(documentEntity)).thenReturn(documentDto);

        DocumentDto result = documentService.update(1L, documentDto);

        assertArrayEquals("sample content".getBytes(), result.content());
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(documentRepository).deleteById(1L);

        documentService.delete(1L);

        verify(documentRepository, times(1)).deleteById(1L);
    }
}
