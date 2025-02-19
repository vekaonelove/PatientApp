package com.orion.patient.service;

import com.orion.patient.dto.CountryDto;
import com.orion.patient.entity.CountryEntity;
import com.orion.patient.mapper.CountryMapper;
import com.orion.patient.repository.CountryRepository;
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
class CountryServiceTests {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryService countryService;

    private CountryEntity countryEntity;
    private CountryDto countryDto;

    @BeforeEach
    void setUp() {
        countryEntity = new CountryEntity();
        countryEntity.setName("USA");
        countryDto = new CountryDto("USA");
    }

    @Test
    void findAll_ShouldReturnListOfCountryDtos() {
        when(countryRepository.findAll()).thenReturn(List.of(countryEntity));
        when(countryMapper.toDto(countryEntity)).thenReturn(countryDto);

        List<CountryDto> result = countryService.findAll();

        assertEquals(1, result.size());
        assertEquals("USA", result.get(0).name());
    }

    @Test
    void findByName_ShouldReturnCountryDto_WhenCountryExists() {
        when(countryRepository.findByName("USA")).thenReturn(countryEntity);
        when(countryMapper.toDto(countryEntity)).thenReturn(countryDto);

        Optional<CountryDto> result = countryService.findByName("USA");

        assertTrue(result.isPresent());
        assertEquals("USA", result.get().name());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenCountryDoesNotExist() {
        when(countryRepository.findByName("USA")).thenReturn(null);

        Optional<CountryDto> result = countryService.findByName("USA");

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedCountryDto() {
        when(countryMapper.toEntity(countryDto)).thenReturn(countryEntity);
        when(countryRepository.save(countryEntity)).thenReturn(countryEntity);
        when(countryMapper.toDto(countryEntity)).thenReturn(countryDto);

        CountryDto result = countryService.save(countryDto);

        assertEquals("USA", result.name());
    }

    @Test
    void update_ShouldReturnUpdatedCountryDto() {
        when(countryMapper.toEntity(countryDto)).thenReturn(countryEntity);
        when(countryRepository.save(countryEntity)).thenReturn(countryEntity);
        when(countryMapper.toDto(countryEntity)).thenReturn(countryDto);

        CountryDto result = countryService.update("USA", countryDto);

        assertEquals("USA", result.name());
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(countryRepository).deleteById("USA");

        countryService.delete("USA");

        verify(countryRepository, times(1)).deleteById("USA");
    }
}
