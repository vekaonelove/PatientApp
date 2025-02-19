package com.orion.patient.controller;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.dto.PatientDto;
import com.orion.patient.service.AppointmentService;
import com.orion.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientControllerTests {

    @Mock
    private PatientService patientService;

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPatients() {
        PatientDto patient1 = new PatientDto(1L, "john.doe@example.com", "John", "Doe", new Date(90, 5, 15), "Male", "+1234567890", 123456789L, "USA", 101L, "123 Main St", 1001L);
        PatientDto patient2 = new PatientDto(2L, "jane.smith@example.com", "Jane", "Smith", new Date(85, 8, 20), "Female", "+1987654321", 987654321L, "Canada", 102L, "456 Maple Ave", 1002L);

        List<PatientDto> patientList = Arrays.asList(patient1, patient2);
        Page<PatientDto> patientPage = new PageImpl<>(patientList, PageRequest.of(0, 10), patientList.size()); // Fix pagination

        when(patientService.getPatients(0, 10)).thenReturn(patientPage); // Mock properly

        // Call controller method (no ResponseEntity)
        Page<PatientDto> response = patientController.getPatients(0, 10);

        // Assertions
        assertThat(response).isNotNull();
        assertThat(response.getContent()).hasSize(2);
        verify(patientService, times(1)).getPatients(0, 10);
    }




    @Test
    void testGetPatientById() {
        Long patientId = 1L;

        PatientDto patient = new PatientDto(1L, "john.doe@example.com", "John", "Doe", new Date(90, 5, 15), "Male", "+1234567890", 123456789L, "USA", 101L, "123 Main St", 1001L);

        when(patientService.getPatient(patientId)).thenReturn(patient);

        ResponseEntity<PatientDto> response = patientController.getPatientById(patientId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().firstName()).isEqualTo("John");
        verify(patientService, times(1)).getPatient(patientId);
    }

    @Test
    void testSavePatient() {
        PatientDto newPatient = new PatientDto(null, "alice.brown@example.com", "Alice", "Brown", new Date(95, 2, 10), "Female", "+1123456789", 456789123L, "UK", 103L, "789 Elm St", 1003L);
        PatientDto savedPatient = new PatientDto(3L, "alice.brown@example.com", "Alice", "Brown", new Date(95, 2, 10), "Female", "+1123456789", 456789123L, "UK", 103L, "789 Elm St", 1003L);

        when(patientService.save(any(PatientDto.class))).thenReturn(savedPatient);

        ResponseEntity<PatientDto> response = patientController.savePatient(newPatient);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).id()).isNotNull();
        assertThat(response.getBody().firstName()).isEqualTo("Alice");
        verify(patientService, times(1)).save(newPatient);
    }

    @Test
    void testUpdatePatient() {
        Long patientId = 1L;
        PatientDto updatedPatient = new PatientDto(1L, "bob.miller@example.com", "Bob", "Miller", new Date(88, 11, 25), "Male", "+1444555666", 654321987L, "Germany", 104L, "321 Oak St", 1004L);

        when(patientService.update(eq(patientId), any(PatientDto.class))).thenReturn(updatedPatient);

        ResponseEntity<PatientDto> response = patientController.updatePatient(patientId, updatedPatient);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).firstName()).isEqualTo("Bob");
        verify(patientService, times(1)).update(patientId, updatedPatient);
    }

    @Test
    void testGetAppointmentsForPatient() {
        Long patientId = 1L;
        AppointmentDto appointment1 = new AppointmentDto(1L, patientId, 101L, 201L, 301L, 401L, LocalDateTime.now().plusDays(1));
        AppointmentDto appointment2 = new AppointmentDto(2L, patientId, 102L, 202L, 302L, 402L, LocalDateTime.now().plusDays(2));

        when(appointmentService.getAppointmentsForPatient(patientId)).thenReturn(Arrays.asList(appointment1, appointment2));

        ResponseEntity<List<AppointmentDto>> response = patientController.getAppointmentsForPatient(patientId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        verify(appointmentService, times(1)).getAppointmentsForPatient(patientId);
    }
}
