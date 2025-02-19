package com.orion.patient.controller;

import com.orion.patient.dto.PatientDto;
import com.orion.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterControllerTests {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private RegisterController registerController;

    private PatientDto patientDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientDto = PatientDto.builder()
                .id(1L)
                .email("john.doe@example.com")
                .firstName("John")
                .lastName("Doe")
                .birthDate(new Date())
                .gender("Male")
                .phoneNumber("+1234567890")
                .ssn(123456789L)
                .countryName("CountryName")
                .cityId(1L)
                .address("123 Main St")
                .contactId(1L)
                .build();
    }

    @Test
    void testRegisterPatient() {
        when(patientService.patientExists(patientDto)).thenReturn(false);
        when(patientService.save(patientDto)).thenReturn(patientDto);

        ResponseEntity<?> response = registerController.registerPatient(patientDto);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Patient registered successfully"));
        verify(patientService, times(1)).patientExists(patientDto);
        verify(patientService, times(1)).save(patientDto);
    }

    @Test
    void testRegisterPatient_AlreadyExists() {
        when(patientService.patientExists(patientDto)).thenReturn(true);

        ResponseEntity<?> response = registerController.registerPatient(patientDto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Patient already exists", response.getBody());
        verify(patientService, times(1)).patientExists(patientDto);
        verify(patientService, times(0)).save(patientDto);
    }

    @Test
    void testGetPatientProfile() {
        when(patientService.getPatient(1L)).thenReturn(patientDto);

        ResponseEntity<?> response = registerController.getPatientProfile(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(patientDto, response.getBody());
        verify(patientService, times(1)).getPatient(1L);
    }
}
