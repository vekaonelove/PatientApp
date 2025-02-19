package com.orion.patient.service;

import com.orion.patient.dto.PatientRecordDto;
import com.orion.patient.entity.PatientRecordEntity;
import com.orion.patient.mapper.PatientRecordMapper;
import com.orion.patient.repository.PatientRecordRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientRecordService {

    private final PatientRecordRepository patientRecordRepository;
    private final PatientRecordMapper patientRecordMapper;

    public PatientRecordService(PatientRecordRepository patientRecordRepository, PatientRecordMapper patientRecordMapper) {
        this.patientRecordRepository = patientRecordRepository;
        this.patientRecordMapper = patientRecordMapper;
    }

    public List<PatientRecordDto> findAll() {
        List<PatientRecordEntity> records = patientRecordRepository.findAll();
        return records.stream()
                .map(patientRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PatientRecordDto> findById(Long id) {
        Optional<PatientRecordEntity> recordEntity = patientRecordRepository.findById(id);
        return recordEntity.map(patientRecordMapper::toDto);
    }

    public PatientRecordDto save(PatientRecordDto patientRecordDTO) {
        PatientRecordEntity patientRecordEntity = patientRecordMapper.toEntity(patientRecordDTO);
        PatientRecordEntity savedEntity = patientRecordRepository.save(patientRecordEntity);
        return patientRecordMapper.toDto(savedEntity);
    }

    public PatientRecordDto update(Long id, @Valid PatientRecordDto patientRecordDTO) {
        PatientRecordEntity patientRecordEntity = patientRecordMapper.toEntity(patientRecordDTO);
        patientRecordEntity.setId(id);
        PatientRecordEntity updatedEntity = patientRecordRepository.save(patientRecordEntity);
        return patientRecordMapper.toDto(updatedEntity);
    }

    public void delete(Long id) {
        patientRecordRepository.deleteById(id);
    }
}
