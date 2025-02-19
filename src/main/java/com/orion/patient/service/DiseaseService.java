package com.orion.patient.service;

import com.orion.patient.dto.DiseaseDto;
import com.orion.patient.entity.DiseaseEntity;
import com.orion.patient.mapper.DiseaseMapper;
import com.orion.patient.repository.DiseaseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final DiseaseMapper diseaseMapper;

    public DiseaseService(DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
    }

    public List<DiseaseDto> findAll() {
        List<DiseaseEntity> diseases = diseaseRepository.findAll();
        return diseases.stream()
                .map(diseaseMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DiseaseDto> findById(Long id) {
        Optional<DiseaseEntity> diseaseEntity = diseaseRepository.findById(id);
        return diseaseEntity.map(diseaseMapper::toDto);
    }

    public DiseaseDto save(DiseaseDto diseaseDTO) {
        DiseaseEntity diseaseEntity = diseaseMapper.toEntity(diseaseDTO);
        DiseaseEntity savedDisease = diseaseRepository.save(diseaseEntity);
        return diseaseMapper.toDto(savedDisease);
    }

    public DiseaseDto update(Long id, @Valid DiseaseDto diseaseDTO) {
        DiseaseEntity diseaseEntity = diseaseMapper.toEntity(diseaseDTO);
        diseaseEntity.setId(id);
        DiseaseEntity updatedDisease = diseaseRepository.save(diseaseEntity);
        return diseaseMapper.toDto(updatedDisease);
    }

    public void delete(Long id) {
        diseaseRepository.deleteById(id);
    }
}
