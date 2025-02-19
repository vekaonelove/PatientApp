package com.orion.patient.service;

import com.orion.patient.dto.PatientStatusDto;
import com.orion.patient.entity.PatientStatusEntity;
import com.orion.patient.mapper.PatientStatusMapper;
import com.orion.patient.repository.PatientStatusRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientStatusService {

    private final PatientStatusRepository patientStatusRepository;
    private final PatientStatusMapper patientStatusMapper;

    public PatientStatusService(PatientStatusRepository patientStatusRepository, PatientStatusMapper patientStatusMapper) {
        this.patientStatusRepository = patientStatusRepository;
        this.patientStatusMapper = patientStatusMapper;
    }

    public List<PatientStatusDto> findAll() {
        List<PatientStatusEntity> patientStatuses = patientStatusRepository.findAll();
        return patientStatuses.stream()
                .map(patientStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PatientStatusDto> findByStatus(String status) {
        Optional<PatientStatusEntity> patientStatusEntity = patientStatusRepository.findById(status);
        return patientStatusEntity.map(patientStatusMapper::toDto);
    }

    public PatientStatusDto save(PatientStatusDto patientStatusDTO) {
        PatientStatusEntity patientStatusEntity = patientStatusMapper.toEntity(patientStatusDTO);
        PatientStatusEntity savedPatientStatus = patientStatusRepository.save(patientStatusEntity);
        return patientStatusMapper.toDto(savedPatientStatus);
    }

    public PatientStatusDto update(String status, @Valid PatientStatusDto patientStatusDTO) {
        PatientStatusEntity patientStatusEntity = patientStatusMapper.toEntity(patientStatusDTO);
        patientStatusEntity.setStatus(status);
        PatientStatusEntity updatedPatientStatus = patientStatusRepository.save(patientStatusEntity);
        return patientStatusMapper.toDto(updatedPatientStatus);
    }

    public void delete(String status) {
        patientStatusRepository.deleteById(status);
    }
}
