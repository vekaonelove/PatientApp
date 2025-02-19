package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.entity.DiseaseEntity;
import com.orion.patient.entity.DocumentEntity;
import com.orion.patient.entity.CountryEntity;
import com.orion.patient.entity.CityEntity;
import com.orion.patient.entity.EmergencyContactEntity;
import com.orion.patient.mapper.AppointmentMapper;
import com.orion.patient.repository.AppointmentRepository;
import com.orion.patient.service.AppointmentService;
import com.orion.patient.util.exception.PatientApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Executable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTests {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentDto appointmentDto;
    private AppointmentEntity appointmentEntity;
    private CountryEntity countryEntity;
    private CityEntity cityEntity;
    private EmergencyContactEntity emergencyContactEntity;

    @BeforeEach
    public void setUp() {
        countryEntity = new CountryEntity("USA");
        cityEntity = new CityEntity(1L, countryEntity, "New York");
        emergencyContactEntity = new EmergencyContactEntity(1L, "Jane Doe", "123-456-7890", "none",
                "mom");

        appointmentDto = new AppointmentDto(
                1L, 1L, 1L, 1L, 1L, 1L, LocalDateTime.now().plusDays(1)
        );

        PatientEntity patientEntity = mock(PatientEntity.class);
        DiseaseEntity diseaseEntity = mock(DiseaseEntity.class);
        DocumentEntity documentEntity = mock(DocumentEntity.class);

        appointmentEntity = new AppointmentEntity(
                1L, patientEntity, 1L, 1L, diseaseEntity, documentEntity, LocalDateTime.now().plusDays(1)
        );
    }

    @Test
    void testFindAll() {
        List<AppointmentEntity> entities = List.of(appointmentEntity);
        when(appointmentRepository.findAll()).thenReturn(entities);
        when(appointmentMapper.toDto(appointmentEntity)).thenReturn(appointmentDto);

        List<AppointmentDto> result = appointmentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(appointmentDto, result.get(0));
    }

    @Test
    public void testFindById_Success() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentMapper.toDto(appointmentEntity)).thenReturn(appointmentDto);

        Optional<AppointmentDto> result = appointmentService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(appointmentDto, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AppointmentDto> result = appointmentService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        when(appointmentMapper.toEntity(appointmentDto)).thenReturn(appointmentEntity);
        when(appointmentRepository.save(appointmentEntity)).thenReturn(appointmentEntity);
        when(appointmentMapper.toDto(appointmentEntity)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.save(appointmentDto);

        assertNotNull(result);
        assertEquals(appointmentDto, result);
    }

    @Test
    public void testUpdate_DoctorNotAvailable() {
        when(appointmentService.isDoctorNotAvailable(
                appointmentDto.doctorId(), appointmentDto.clinicId(), appointmentDto.appointmentTime()
        )).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                appointmentService.update(1L, appointmentDto)
        );
        assertEquals("Doctor is not available at the specified time.", exception.getMessage());
    }


    @Test
    public void testDelete() {
        doNothing().when(appointmentRepository).deleteById(1L);

        appointmentService.delete(1L);

        verify(appointmentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAppointmentsForPatient() {
        List<AppointmentEntity> entities = List.of(appointmentEntity);
        when(appointmentRepository.findByPatientId(1L)).thenReturn(entities);
        when(appointmentMapper.toDto(appointmentEntity)).thenReturn(appointmentDto);

        List<AppointmentDto> result = appointmentService.getAppointmentsForPatient(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(appointmentDto, result.get(0));
    }

    @Test
    public void testIsDoctorAvailable_Success() {
        when(appointmentRepository.existsByDoctorIdAndClinicIdAndAppointmentTime(
                1L, 1L, appointmentDto.appointmentTime()
        )).thenReturn(false);

        boolean result = appointmentService.isDoctorNotAvailable(1L, 1L, appointmentDto.appointmentTime());

        assertFalse(result);
    }

    @Test
    public void testIsDoctorAvailable_NotAvailable() {
        when(appointmentRepository.existsByDoctorIdAndClinicIdAndAppointmentTime(
                1L, 1L, appointmentDto.appointmentTime()
        )).thenReturn(true);

        boolean result = appointmentService.isDoctorNotAvailable(1L, 1L, appointmentDto.appointmentTime());

        assertTrue(result);
    }

    @Test
    public void testCreateAppointment() {
        when(appointmentMapper.toEntity(appointmentDto)).thenReturn(appointmentEntity);
        when(appointmentRepository.save(appointmentEntity)).thenReturn(appointmentEntity);

        appointmentService.createAppointment(appointmentDto);

        verify(appointmentRepository, times(1)).save(appointmentEntity);
    }
}
