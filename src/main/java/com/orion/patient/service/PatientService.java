package com.orion.patient.service;

import com.orion.patient.dto.PatientDto;
import com.orion.patient.entity.PatientEntity;
import com.orion.patient.util.exception.PatientApplicationException;
import com.orion.patient.mapper.PatientMapper;
import com.orion.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<PatientDto> getPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PatientEntity> patientPage = patientRepository.findAll(pageable);

        return patientPage.map(patientMapper::toDto);
    }

    public PatientDto getPatient(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new PatientApplicationException("Patient with ID " + id + " not found"));
    }

    public PatientDto save(PatientDto patientDTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return patientMapper.toDto(savedPatient);
    }

    public PatientDto update(Long id, @Valid PatientDto patientDTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientDTO);
        patientEntity.setId(id);
        PatientEntity updatedEntity = patientRepository.save(patientEntity);
        return patientMapper.toDto(updatedEntity);
    }

    public boolean patientExists(PatientDto patientDTO) {
        return patientRepository.existsByEmailOrPhoneNumber(patientDTO.email(), patientDTO.phoneNumber());
    }
}
