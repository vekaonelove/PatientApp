package com.orion.patient.service;

import com.orion.patient.dto.PatientDto;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.mapper.PatientMapper;
import com.orion.patient.repository.PatientRepository;
import com.orion.patient.util.exception.PatientApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTests {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService;

    private PatientDto patientDto;
    private PatientEntity patientEntity;

    @BeforeEach
    void setUp() {
        patientDto = new PatientDto(1L, "john.doe@example.com", "John", "Doe", new Date(90, 5, 15), "Male", "+1234567890", 123456789L, "USA", 101L, "123 Main St", 1001L);
        patientEntity = new PatientEntity();
        patientEntity.setId(1L);
        patientEntity.setEmail("john.doe@example.com");
    }

    @Test
    void testGetPatients() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PatientEntity> patientPage = new PageImpl<>(Collections.singletonList(patientEntity));

        when(patientRepository.findAll(pageable)).thenReturn(patientPage);
        when(patientMapper.toDto(any(PatientEntity.class))).thenReturn(patientDto);

        Page<PatientDto> result = patientService.getPatients(0, 10);

        assertThat(result.getContent()).hasSize(1);
        verify(patientRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetPatientById_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientEntity));
        when(patientMapper.toDto(patientEntity)).thenReturn(patientDto);

        PatientDto result = patientService.getPatient(1L);

        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo("john.doe@example.com");
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientApplicationException.class, () -> patientService.getPatient(1L));
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testSavePatient() {
        when(patientMapper.toEntity(patientDto)).thenReturn(patientEntity);
        when(patientRepository.save(patientEntity)).thenReturn(patientEntity);
        when(patientMapper.toDto(patientEntity)).thenReturn(patientDto);

        PatientDto result = patientService.save(patientDto);

        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo("john.doe@example.com");
        verify(patientRepository, times(1)).save(patientEntity);
    }

    @Test
    void testUpdatePatient() {
        when(patientMapper.toEntity(patientDto)).thenReturn(patientEntity);
        when(patientRepository.save(patientEntity)).thenReturn(patientEntity);
        when(patientMapper.toDto(patientEntity)).thenReturn(patientDto);

        PatientDto result = patientService.update(1L, patientDto);

        assertThat(result).isNotNull();
        verify(patientRepository, times(1)).save(patientEntity);
    }

    @Test
    void testPatientExists() {
        when(patientRepository.existsByEmailOrPhoneNumber("john.doe@example.com", "+1234567890")).thenReturn(true);

        boolean exists = patientService.patientExists(patientDto);

        assertThat(exists).isTrue();
        verify(patientRepository, times(1)).existsByEmailOrPhoneNumber("john.doe@example.com", "+1234567890");
    }
}
