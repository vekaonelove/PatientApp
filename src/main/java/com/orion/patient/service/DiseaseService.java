package com.orion.patient.service;

import com.orion.patient.dto.DiseaseDTO;
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

    public List<DiseaseDTO> findAll() {
        List<DiseaseEntity> diseases = diseaseRepository.findAll();
        return diseases.stream()
                .map(diseaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DiseaseDTO> findById(Long id) {
        Optional<DiseaseEntity> diseaseEntity = diseaseRepository.findById(id);
        return diseaseEntity.map(diseaseMapper::toDTO);
    }

    public DiseaseDTO save(DiseaseDTO diseaseDTO) {
        DiseaseEntity diseaseEntity = diseaseMapper.toEntity(diseaseDTO);
        DiseaseEntity savedDisease = diseaseRepository.save(diseaseEntity);
        return diseaseMapper.toDTO(savedDisease);
    }

    public DiseaseDTO update(Long id, @Valid DiseaseDTO diseaseDTO) {
        DiseaseEntity diseaseEntity = diseaseMapper.toEntity(diseaseDTO);
        diseaseEntity.setId(id);
        DiseaseEntity updatedDisease = diseaseRepository.save(diseaseEntity);
        return diseaseMapper.toDTO(updatedDisease);
    }

    public void delete(Long id) {
        diseaseRepository.deleteById(id);
    }
}
