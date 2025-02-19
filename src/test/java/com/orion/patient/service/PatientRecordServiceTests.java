package com.orion.patient.service;

import com.orion.patient.dto.PatientRecordDto;
import com.orion.patient.entity.DiseaseEntity;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.entity.PatientRecordEntity;
import com.orion.patient.mapper.PatientRecordMapper;
import com.orion.patient.repository.PatientRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientRecordServiceTests {

    @Mock
    private PatientRecordRepository patientRecordRepository;

    @Mock
    private PatientRecordMapper patientRecordMapper;

    @InjectMocks
    private PatientRecordService patientRecordService;

    private PatientRecordEntity patientRecordEntity;
    private PatientRecordDto patientRecordDto;

    @BeforeEach
    void setUp() {
        PatientEntity patient = new PatientEntity(1L, "Mary", "Jane", new Date(), "F", "123456789", 123456789L, "mary.jane@example.com", "123 Main St", null, null, null, 1L);

        patientRecordEntity = new PatientRecordEntity();
        patientRecordEntity.setId(1L);
        patientRecordEntity.setPatient(patient);
        patientRecordEntity.setDisease(new DiseaseEntity());
        patientRecordEntity.setDateStart(Instant.now());
        patientRecordEntity.setDateEnd(Instant.now());

        patientRecordDto = new PatientRecordDto(1L, 1L, 202L, Instant.now(), Instant.now());
    }

    @Test
    void findAll_ShouldReturnListOfPatientRecordDtos() {
        when(patientRecordRepository.findAll()).thenReturn(List.of(patientRecordEntity));
        when(patientRecordMapper.toDto(patientRecordEntity)).thenReturn(patientRecordDto);

        List<PatientRecordDto> result = patientRecordService.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
    }

    @Test
    void findById_ShouldReturnPatientRecordDto_WhenRecordExists() {
        when(patientRecordRepository.findById(1L)).thenReturn(Optional.of(patientRecordEntity));
        when(patientRecordMapper.toDto(patientRecordEntity)).thenReturn(patientRecordDto);

        Optional<PatientRecordDto> result = patientRecordService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenRecordDoesNotExist() {
        when(patientRecordRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<PatientRecordDto> result = patientRecordService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedPatientRecordDto() {
        when(patientRecordMapper.toEntity(patientRecordDto)).thenReturn(patientRecordEntity);
        when(patientRecordRepository.save(patientRecordEntity)).thenReturn(patientRecordEntity);
        when(patientRecordMapper.toDto(patientRecordEntity)).thenReturn(patientRecordDto);

        PatientRecordDto result = patientRecordService.save(patientRecordDto);

        assertEquals(1L, result.id());
    }

    @Test
    void update_ShouldReturnUpdatedPatientRecordDto() {
        when(patientRecordMapper.toEntity(patientRecordDto)).thenReturn(patientRecordEntity);
        when(patientRecordRepository.save(patientRecordEntity)).thenReturn(patientRecordEntity);
        when(patientRecordMapper.toDto(patientRecordEntity)).thenReturn(patientRecordDto);

        PatientRecordDto result = patientRecordService.update(1L, patientRecordDto);

        assertEquals(1L, result.id());
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(patientRecordRepository).deleteById(1L);

        patientRecordService.delete(1L);

        verify(patientRecordRepository, times(1)).deleteById(1L);
    }
}