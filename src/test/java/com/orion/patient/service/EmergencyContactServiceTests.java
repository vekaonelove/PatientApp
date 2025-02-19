package com.orion.patient.service;

import com.orion.patient.dto.EmergencyContactDto;
import com.orion.patient.entity.EmergencyContactEntity;
import com.orion.patient.mapper.EmergencyContactMapper;
import com.orion.patient.repository.EmergencyContactRepository;
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
class EmergencyContactServiceTests {

    @Mock
    private EmergencyContactRepository emergencyContactRepository;

    @Mock
    private EmergencyContactMapper emergencyContactMapper;

    @InjectMocks
    private EmergencyContactService emergencyContactService;

    private EmergencyContactEntity emergencyContactEntity;
    private EmergencyContactDto emergencyContactDto;

    @BeforeEach
    void setUp() {
        emergencyContactEntity = new EmergencyContactEntity();
        emergencyContactEntity.setId(1L);
        emergencyContactEntity.setName("John Doe");
        emergencyContactEntity.setPhoneNumber("1234567890");
        emergencyContactEntity.setAdditionalContact("9876543210");
        emergencyContactEntity.setRelation("Brother");

        emergencyContactDto = new EmergencyContactDto(1L, "John Doe", "1234567890", "9876543210", "Brother");
    }

    @Test
    void findAll_ShouldReturnListOfEmergencyContactDtos() {
        when(emergencyContactRepository.findAll()).thenReturn(List.of(emergencyContactEntity));
        when(emergencyContactMapper.toDto(emergencyContactEntity)).thenReturn(emergencyContactDto);

        List<EmergencyContactDto> result = emergencyContactService.findAll();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).name());
    }

    @Test
    void findById_ShouldReturnEmergencyContactDto_WhenExists() {
        when(emergencyContactRepository.findById(1L)).thenReturn(Optional.of(emergencyContactEntity));
        when(emergencyContactMapper.toDto(emergencyContactEntity)).thenReturn(emergencyContactDto);

        Optional<EmergencyContactDto> result = emergencyContactService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().name());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(emergencyContactRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<EmergencyContactDto> result = emergencyContactService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedEmergencyContactDto() {
        when(emergencyContactMapper.toEntity(emergencyContactDto)).thenReturn(emergencyContactEntity);
        when(emergencyContactRepository.save(emergencyContactEntity)).thenReturn(emergencyContactEntity);
        when(emergencyContactMapper.toDto(emergencyContactEntity)).thenReturn(emergencyContactDto);

        EmergencyContactDto result = emergencyContactService.save(emergencyContactDto);

        assertEquals("John Doe", result.name());
    }

    @Test
    void update_ShouldReturnUpdatedEmergencyContactDto_WhenExists() {
        when(emergencyContactRepository.existsById(1L)).thenReturn(true);
        when(emergencyContactMapper.toEntity(emergencyContactDto)).thenReturn(emergencyContactEntity);
        when(emergencyContactRepository.save(emergencyContactEntity)).thenReturn(emergencyContactEntity);
        when(emergencyContactMapper.toDto(emergencyContactEntity)).thenReturn(emergencyContactDto);

        EmergencyContactDto result = emergencyContactService.update(1L, emergencyContactDto);

        assertNotNull(result);
        assertEquals("John Doe", result.name());
    }

    @Test
    void update_ShouldReturnNull_WhenNotExists() {
        when(emergencyContactRepository.existsById(1L)).thenReturn(false);

        EmergencyContactDto result = emergencyContactService.update(1L, emergencyContactDto);

        assertNull(result);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(emergencyContactRepository).deleteById(1L);

        emergencyContactService.delete(1L);

        verify(emergencyContactRepository, times(1)).deleteById(1L);
    }
}
