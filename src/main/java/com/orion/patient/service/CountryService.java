package com.orion.patient.service;

import com.orion.patient.dto.CountryDto;
import com.orion.patient.entity.CountryEntity;
import com.orion.patient.mapper.CountryMapper;
import com.orion.patient.repository.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public List<CountryDto> findAll() {
        List<CountryEntity> countries = countryRepository.findAll();
        return countries.stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CountryDto> findByName(String name) {
        Optional<CountryEntity> countryEntity = Optional.ofNullable(countryRepository.findByName(name));
        return countryEntity.map(countryMapper::toDto);
    }

    public CountryDto save(CountryDto countryDTO) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        CountryEntity savedCountry = countryRepository.save(countryEntity);
        return countryMapper.toDto(savedCountry);
    }

    public CountryDto update(String name, @Valid CountryDto countryDTO) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        countryEntity.setName(name);
        CountryEntity updatedCountry = countryRepository.save(countryEntity);
        return countryMapper.toDto(updatedCountry);
    }

    public void delete(String name) {
        countryRepository.deleteById(name);
    }
}
