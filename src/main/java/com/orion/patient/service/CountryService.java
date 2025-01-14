package com.orion.patient.service;

import com.orion.patient.dto.CountryDTO;
import com.orion.patient.entity.CountryEntity;
import com.orion.patient.mapper.CountryMapper;
import com.orion.patient.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public List<CountryDTO> findAll() {
        List<CountryEntity> countries = countryRepository.findAll();
        return countries.stream()
                .map(countryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CountryDTO> findByName(String name) {
        Optional<CountryEntity> countryEntity = Optional.ofNullable(countryRepository.findByName(name));
        return countryEntity.map(countryMapper::toDTO);
    }

    public CountryDTO save(CountryDTO countryDTO) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        CountryEntity savedCountry = countryRepository.save(countryEntity);
        return countryMapper.toDTO(savedCountry);
    }

    public CountryDTO update(String name, CountryDTO countryDTO) {
        Optional<CountryEntity> existingCountry = Optional.ofNullable(countryRepository.findByName(name));

        if (existingCountry.isEmpty()) {
            return null;
        }

        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        countryEntity.setName(name);
        CountryEntity updatedCountry = countryRepository.save(countryEntity);
        return countryMapper.toDTO(updatedCountry);
    }

    public void delete(String name) {
        countryRepository.deleteById(name);
    }
}
