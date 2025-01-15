package com.orion.patient.service;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.mapper.PatientMapper;
import com.orion.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PatientDTO> findById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO);
    }

    public PatientDTO save(PatientDTO patientDTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return patientMapper.toDTO(savedPatient);
    }
}
