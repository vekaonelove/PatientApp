package com.orion.patient.service;

import com.orion.patient.dto.PatientDTO;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.util.exception.DuplicatePatientException;
import com.orion.patient.util.exception.PatientNotFoundException;
import com.orion.patient.mapper.PatientMapper;
import com.orion.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

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

    public PatientDTO findById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
    }

    public PatientDTO save(PatientDTO patientDTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return patientMapper.toDTO(savedPatient);
    }

    public PatientDTO update(Long id, @Valid PatientDTO patientDTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        patientEntity.setId(id);
        PatientEntity updatedEntity = patientRepository.save(patientEntity);
        return patientMapper.toDTO(updatedEntity);
    }

    public boolean patientExists(PatientDTO patientDTO) {
        return patientRepository.existsByEmailOrPhoneNumber(patientDTO.email(), patientDTO.phoneNumber());
    }

    public void registerPatient(PatientDTO patientDTO) {
        if (patientExists(patientDTO)) {
            throw new DuplicatePatientException("Patient with this email or phone number already exists");
        }

        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        patientRepository.save(patientEntity);
    }
}
