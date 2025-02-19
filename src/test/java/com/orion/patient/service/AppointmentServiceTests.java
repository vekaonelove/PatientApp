package com.orion.patient.service;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.entity.DiseaseEntity;
import com.orion.patient.entity.DocumentEntity;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.mapper.AppointmentMapper;
import com.orion.patient.repository.AppointmentRepository;
import com.orion.patient.util.exception.PatientApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppointmentServiceTests {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentDto appointmentDto;
    private AppointmentEntity appointmentEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appointmentDto = new AppointmentDto(1L, 1L, 1L, 1L, 1L, 1L, LocalDateTime.now().plusHours(1));
        appointmentEntity = new AppointmentEntity(1L, new PatientEntity(), 1L, 1L, new DiseaseEntity(), new DocumentEntity(), LocalDateTime.now().plusHours(1));
    }

    @Test
    void testFindAll() {
        when(appointmentRepository.findAll()).thenReturn(List.of(appointmentEntity));
        when(appointmentMapper.toDto(any(AppointmentEntity.class))).thenReturn(appointmentDto);

        var result = appointmentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(appointmentDto, result.get(0));
    }

    @Test
    void testFindById() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentMapper.toDto(any(AppointmentEntity.class))).thenReturn(appointmentDto);

        var result = appointmentService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(appointmentDto, result.get());
    }

    @Test
    void testSave() {
        when(appointmentMapper.toEntity(any(AppointmentDto.class))).thenReturn(appointmentEntity);
        when(appointmentRepository.save(any(AppointmentEntity.class))).thenReturn(appointmentEntity);
        when(appointmentMapper.toDto(any(AppointmentEntity.class))).thenReturn(appointmentDto);

        var result = appointmentService.save(appointmentDto);

        assertNotNull(result);
        assertEquals(appointmentDto, result);
    }

    @Test
    void testUpdate() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentRepository.save(any(AppointmentEntity.class))).thenReturn(appointmentEntity);
        when(appointmentMapper.toDto(any(AppointmentEntity.class))).thenReturn(appointmentDto);
        when(appointmentService.isDoctorNotAvailable(any(), any(), any())).thenReturn(true);

        var result = appointmentService.update(1L, new AppointmentDto(5L,5L,1L,1L,1L,1L,
                LocalDateTime.now().plusHours(10)));

        assertNotNull(result);
        assertEquals(appointmentDto, result);
    }


    @Test
    void testUpdateDoctorNotAvailable() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentService.isDoctorNotAvailable(any(), any(), any())).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> appointmentService.update(1L, appointmentDto));
        assertEquals("Doctor is not available at the specified time.", thrown.getMessage());
    }


    @Test
    void testUpdateAppointmentNotFound() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientApplicationException.class, () -> appointmentService.update(1L, appointmentDto));
    }

    @Test
    void testDelete() {
        doNothing().when(appointmentRepository).deleteById(1L);

        appointmentService.delete(1L);

        verify(appointmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAppointmentsForPatient() {
        when(appointmentRepository.findByPatientId(1L)).thenReturn(List.of(appointmentEntity));
        when(appointmentMapper.toDto(any(AppointmentEntity.class))).thenReturn(appointmentDto);
        var result = appointmentService.getAppointmentsForPatient(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(appointmentDto, result.get(0));
    }

    @Test
    void testIsDoctorNotAvailable() {
        when(appointmentRepository.existsByDoctorIdAndClinicIdAndAppointmentTime(any(), any(), any())).thenReturn(false);
        var result = appointmentService.isDoctorNotAvailable(1L, 2L, LocalDateTime.now().plusHours(1));
        assertFalse(result);
    }
}
