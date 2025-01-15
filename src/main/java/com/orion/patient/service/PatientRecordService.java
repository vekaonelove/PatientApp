package com.orion.patient.service;

import com.orion.patient.dto.PatientRecordDTO;
import com.orion.patient.entity.PatientRecordEntity;
import com.orion.patient.mapper.PatientRecordMapper;
import com.orion.patient.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientRecordService {

    private final PatientRecordRepository patientRecordRepository;
    private final PatientRecordMapper patientRecordMapper;

    @Autowired
    public PatientRecordService(PatientRecordRepository patientRecordRepository, PatientRecordMapper patientRecordMapper) {
        this.patientRecordRepository = patientRecordRepository;
        this.patientRecordMapper = patientRecordMapper;
    }

    public List<PatientRecordDTO> findAll() {
        List<PatientRecordEntity> records = patientRecordRepository.findAll();
        return records.stream()
                .map(patientRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PatientRecordDTO> findById(Long id) {
        Optional<PatientRecordEntity> recordEntity = patientRecordRepository.findById(id);
        return recordEntity.map(patientRecordMapper::toDTO);
    }

    public PatientRecordDTO save(PatientRecordDTO patientRecordDTO) {
        PatientRecordEntity patientRecordEntity = patientRecordMapper.toEntity(patientRecordDTO);
        PatientRecordEntity savedEntity = patientRecordRepository.save(patientRecordEntity);
        return patientRecordMapper.toDTO(savedEntity);
    }

    public PatientRecordDTO update(Long id, PatientRecordDTO patientRecordDTO) {
        if (!patientRecordRepository.existsById(id)) {
            return null;
        }
        PatientRecordEntity patientRecordEntity = patientRecordMapper.toEntity(patientRecordDTO);
        patientRecordEntity.setId(id);
        PatientRecordEntity updatedEntity = patientRecordRepository.save(patientRecordEntity);
        return patientRecordMapper.toDTO(updatedEntity);
    }

    public void delete(Long id) {
        patientRecordRepository.deleteById(id);
    }
}
