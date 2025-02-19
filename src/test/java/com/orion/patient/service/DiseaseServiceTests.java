package com.orion.patient.service;

import com.orion.patient.dto.DiseaseDto;
import com.orion.patient.entity.DiseaseEntity;
import com.orion.patient.mapper.DiseaseMapper;
import com.orion.patient.repository.DiseaseRepository;
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
class DiseaseServiceTests {

    @Mock
    private DiseaseRepository diseaseRepository;

    @Mock
    private DiseaseMapper diseaseMapper;

    @InjectMocks
    private DiseaseService diseaseService;

    private DiseaseEntity diseaseEntity;
    private DiseaseDto diseaseDto;

    @BeforeEach
    void setUp() {
        diseaseEntity = new DiseaseEntity();
        diseaseEntity.setId(1L);
        diseaseEntity.setName("Flu");
        diseaseEntity.setCodeIcd("J10");

        diseaseDto = new DiseaseDto(1L, "Flu", "J10");
    }

    @Test
    void findAll_ShouldReturnListOfDiseaseDtos() {
        when(diseaseRepository.findAll()).thenReturn(List.of(diseaseEntity));
        when(diseaseMapper.toDto(diseaseEntity)).thenReturn(diseaseDto);

        List<DiseaseDto> result = diseaseService.findAll();

        assertEquals(1, result.size());
        assertEquals("Flu", result.get(0).name());
    }

    @Test
    void findById_ShouldReturnDiseaseDto_WhenDiseaseExists() {
        when(diseaseRepository.findById(1L)).thenReturn(Optional.of(diseaseEntity));
        when(diseaseMapper.toDto(diseaseEntity)).thenReturn(diseaseDto);

        Optional<DiseaseDto> result = diseaseService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Flu", result.get().name());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenDiseaseDoesNotExist() {
        when(diseaseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<DiseaseDto> result = diseaseService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedDiseaseDto() {
        when(diseaseMapper.toEntity(diseaseDto)).thenReturn(diseaseEntity);
        when(diseaseRepository.save(diseaseEntity)).thenReturn(diseaseEntity);
        when(diseaseMapper.toDto(diseaseEntity)).thenReturn(diseaseDto);

        DiseaseDto result = diseaseService.save(diseaseDto);

        assertEquals("Flu", result.name());
    }

    @Test
    void update_ShouldReturnUpdatedDiseaseDto() {
        when(diseaseMapper.toEntity(diseaseDto)).thenReturn(diseaseEntity);
        when(diseaseRepository.save(diseaseEntity)).thenReturn(diseaseEntity);
        when(diseaseMapper.toDto(diseaseEntity)).thenReturn(diseaseDto);

        DiseaseDto result = diseaseService.update(1L, diseaseDto);

        assertEquals("Flu", result.name());
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(diseaseRepository).deleteById(1L);

        diseaseService.delete(1L);

        verify(diseaseRepository, times(1)).deleteById(1L);
    }
}
