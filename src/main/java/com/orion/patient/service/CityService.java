package com.orion.patient.service;

import com.orion.patient.dto.CityDto;
import com.orion.patient.entity.CityEntity;
import com.orion.patient.mapper.CityMapper;
import com.orion.patient.repository.CityRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDto> findAll() {
        List<CityEntity> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CityDto> findById(Long id) {
        Optional<CityEntity> cityEntity = cityRepository.findById(id);
        return cityEntity.map(cityMapper::toDto);
    }

    public CityDto save(CityDto cityDTO) {
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        CityEntity savedCity = cityRepository.save(cityEntity);
        return cityMapper.toDto(savedCity);
    }

    public CityDto update(Long id, @Valid CityDto cityDTO) {
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        cityEntity.setId(id);
        CityEntity updatedCity = cityRepository.save(cityEntity);
        return cityMapper.toDto(updatedCity);
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
