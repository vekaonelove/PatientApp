package com.orion.patient.service;

import com.orion.patient.dto.PatientStatusDto;
import com.orion.patient.entity.PatientStatusEntity;
import com.orion.patient.mapper.PatientStatusMapper;
import com.orion.patient.repository.PatientStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientStatusServiceTests {

    @Mock
    private PatientStatusRepository patientStatusRepository;

    @Mock
    private PatientStatusMapper patientStatusMapper;

    @InjectMocks
    private PatientStatusService patientStatusService;

    private PatientStatusEntity patientStatusEntity;
    private PatientStatusDto patientStatusDto;

    @BeforeEach
    void setUp() {
        patientStatusEntity = new PatientStatusEntity("Active");
        patientStatusDto = new PatientStatusDto("Active");
    }

    @Test
    void testFindAll() {
        when(patientStatusRepository.findAll()).thenReturn(List.of(patientStatusEntity));
        when(patientStatusMapper.toDto(patientStatusEntity)).thenReturn(patientStatusDto);

        List<PatientStatusDto> result = patientStatusService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).status()).isEqualTo("Active");
        verify(patientStatusRepository, times(1)).findAll();
    }

    @Test
    void testFindByStatus() {
        when(patientStatusRepository.findById("Active")).thenReturn(Optional.of(patientStatusEntity));
        when(patientStatusMapper.toDto(patientStatusEntity)).thenReturn(patientStatusDto);

        Optional<PatientStatusDto> result = patientStatusService.findByStatus("Active");

        assertThat(result).isPresent();
        assertThat(result.get().status()).isEqualTo("Active");
        verify(patientStatusRepository, times(1)).findById("Active");
    }

    @Test
    void testSave() {
        when(patientStatusMapper.toEntity(patientStatusDto)).thenReturn(patientStatusEntity);
        when(patientStatusRepository.save(patientStatusEntity)).thenReturn(patientStatusEntity);
        when(patientStatusMapper.toDto(patientStatusEntity)).thenReturn(patientStatusDto);

        PatientStatusDto result = patientStatusService.save(patientStatusDto);

        assertThat(result.status()).isEqualTo("Active");
        verify(patientStatusRepository, times(1)).save(patientStatusEntity);
    }

    @Test
    void testUpdate() {
        when(patientStatusMapper.toEntity(patientStatusDto)).thenReturn(patientStatusEntity);
        when(patientStatusRepository.save(patientStatusEntity)).thenReturn(patientStatusEntity);
        when(patientStatusMapper.toDto(patientStatusEntity)).thenReturn(patientStatusDto);

        PatientStatusDto result = patientStatusService.update("Active", patientStatusDto);

        assertThat(result.status()).isEqualTo("Active");
        verify(patientStatusRepository, times(1)).save(patientStatusEntity);
    }

    @Test
    void testDelete() {
        doNothing().when(patientStatusRepository).deleteById("Active");

        patientStatusService.delete("Active");

        verify(patientStatusRepository, times(1)).deleteById("Active");
    }
}
