package com.orion.patient.service;

import com.orion.patient.dto.StatusRecordDto;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.entity.PatientStatusEntity;
import com.orion.patient.entity.StatusRecordEntity;
import com.orion.patient.mapper.StatusRecordMapper;
import com.orion.patient.repository.StatusRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusRecordServiceTests {

    @Mock
    private StatusRecordRepository statusRecordRepository;

    @Mock
    private StatusRecordMapper statusRecordMapper;

    @InjectMocks
    private StatusRecordService statusRecordService;

    private StatusRecordDto statusRecordDto;
    private StatusRecordEntity statusRecordEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        statusRecordDto = new StatusRecordDto(1L, 123L, "ACTIVE", Instant.now());
        statusRecordEntity = new StatusRecordEntity(1L, new PatientEntity(), new PatientStatusEntity(), Instant.now());
    }

    @Test
    void testFindAll() {
        when(statusRecordRepository.findAll()).thenReturn(List.of(statusRecordEntity));
        when(statusRecordMapper.toDto(statusRecordEntity)).thenReturn(statusRecordDto);
        List<StatusRecordDto> result = statusRecordService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(statusRecordDto.id(), result.get(0).id());
        verify(statusRecordRepository, times(1)).findAll();
        verify(statusRecordMapper, times(1)).toDto(statusRecordEntity);
    }

    @Test
    void testFindById() {
        when(statusRecordRepository.findById(1L)).thenReturn(Optional.of(statusRecordEntity));
        when(statusRecordMapper.toDto(statusRecordEntity)).thenReturn(statusRecordDto);
        Optional<StatusRecordDto> result = statusRecordService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(statusRecordDto.id(), result.get().id());
        verify(statusRecordRepository, times(1)).findById(1L);
        verify(statusRecordMapper, times(1)).toDto(statusRecordEntity);
    }

    @Test
    void testFindById_NotFound() {
        when(statusRecordRepository.findById(999L)).thenReturn(Optional.empty());
        Optional<StatusRecordDto> result = statusRecordService.findById(999L);
        assertFalse(result.isPresent());
        verify(statusRecordRepository, times(1)).findById(999L);
    }

    @Test
    void testSave() {
        when(statusRecordMapper.toEntity(statusRecordDto)).thenReturn(statusRecordEntity);
        when(statusRecordRepository.save(statusRecordEntity)).thenReturn(statusRecordEntity);
        when(statusRecordMapper.toDto(statusRecordEntity)).thenReturn(statusRecordDto);
        StatusRecordDto result = statusRecordService.save(statusRecordDto);
        assertNotNull(result);
        assertEquals(statusRecordDto.id(), result.id());
        verify(statusRecordMapper, times(1)).toEntity(statusRecordDto);
        verify(statusRecordRepository, times(1)).save(statusRecordEntity);
        verify(statusRecordMapper, times(1)).toDto(statusRecordEntity);
    }

    @Test
    void testUpdate() {
        when(statusRecordMapper.toEntity(statusRecordDto)).thenReturn(statusRecordEntity);
        when(statusRecordRepository.save(statusRecordEntity)).thenReturn(statusRecordEntity);
        when(statusRecordMapper.toDto(statusRecordEntity)).thenReturn(statusRecordDto);
        StatusRecordDto result = statusRecordService.update(1L, statusRecordDto);
        assertNotNull(result);
        assertEquals(statusRecordDto.id(), result.id());
        verify(statusRecordRepository, times(1)).save(statusRecordEntity);
        verify(statusRecordMapper, times(1)).toDto(statusRecordEntity);
    }

    @Test
    void testDelete() {
        statusRecordService.delete(1L);
        verify(statusRecordRepository, times(1)).deleteById(1L);
    }
}
