package com.orion.patient.service;

import com.orion.patient.dto.CityDTO;
import com.orion.patient.entity.CityEntity;
import com.orion.patient.mapper.CityMapper;
import com.orion.patient.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDTO> findAll() {
        List<CityEntity> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CityDTO> findById(Long id) {
        Optional<CityEntity> cityEntity = cityRepository.findById(id);
        return cityEntity.map(cityMapper::toDTO);
    }

    public CityDTO save(CityDTO cityDTO) {
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        CityEntity savedCity = cityRepository.save(cityEntity);
        return cityMapper.toDTO(savedCity);
    }

    public CityDTO update(Long id, CityDTO cityDTO) {
        if (!cityRepository.existsById(id)) {
            return null;
        }
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        cityEntity.setId(id);
        CityEntity updatedCity = cityRepository.save(cityEntity);
        return cityMapper.toDTO(updatedCity);
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
