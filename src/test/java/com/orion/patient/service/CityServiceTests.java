package com.orion.patient.service;

import com.orion.patient.dto.CityDto;
import com.orion.patient.entity.CityEntity;
import com.orion.patient.entity.CountryEntity;
import com.orion.patient.mapper.CityMapper;
import com.orion.patient.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityServiceTests {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityService cityService;

    private CityDto cityDto;
    private CityEntity cityEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cityDto = new CityDto(1L, 1L, "CityName");
        cityEntity = new CityEntity(1L, new CountryEntity(), "CityName");
    }

    @Test
    void testFindAll() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(cityEntity));
        when(cityMapper.toDto(cityEntity)).thenReturn(cityDto);

        var result = cityService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cityDto, result.get(0));
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(cityEntity));
        when(cityMapper.toDto(cityEntity)).thenReturn(cityDto);
        var result = cityService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(cityDto, result.get());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(cityMapper.toEntity(cityDto)).thenReturn(cityEntity);
        when(cityRepository.save(cityEntity)).thenReturn(cityEntity);
        when(cityMapper.toDto(cityEntity)).thenReturn(cityDto);

        var result = cityService.save(cityDto);

        assertNotNull(result);
        assertEquals(cityDto, result);
        verify(cityRepository, times(1)).save(cityEntity);
    }

    @Test
    void testUpdate() {
        when(cityMapper.toEntity(cityDto)).thenReturn(cityEntity);
        when(cityRepository.save(cityEntity)).thenReturn(cityEntity);
        when(cityMapper.toDto(cityEntity)).thenReturn(cityDto);

        var result = cityService.update(1L, cityDto);

        assertNotNull(result);
        assertEquals(cityDto, result);
        verify(cityRepository, times(1)).save(cityEntity);
    }

    @Test
    void testDelete() {
        cityService.delete(1L);
        verify(cityRepository, times(1)).deleteById(1L);
    }
}
