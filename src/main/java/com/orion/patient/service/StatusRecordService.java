package com.orion.patient.service;

import com.orion.patient.dto.StatusRecordDTO;
import com.orion.patient.entity.StatusRecordEntity;
import com.orion.patient.mapper.StatusRecordMapper;
import com.orion.patient.repository.StatusRecordRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusRecordService {

    private final StatusRecordRepository statusRecordRepository;
    private final StatusRecordMapper statusRecordMapper;

    public StatusRecordService(StatusRecordRepository statusRecordRepository, StatusRecordMapper statusRecordMapper) {
        this.statusRecordRepository = statusRecordRepository;
        this.statusRecordMapper = statusRecordMapper;
    }

    public List<StatusRecordDTO> findAll() {
        List<StatusRecordEntity> statusRecords = statusRecordRepository.findAll();
        return statusRecords.stream().map(statusRecordMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<StatusRecordDTO> findById(Long id) {
        Optional<StatusRecordEntity> statusRecordEntity = statusRecordRepository.findById(id);
        return statusRecordEntity.map(statusRecordMapper::toDTO);
    }

    public StatusRecordDTO save(StatusRecordDTO statusRecordDTO) {
        StatusRecordEntity statusRecordEntity = statusRecordMapper.toEntity(statusRecordDTO);
        StatusRecordEntity savedStatusRecord = statusRecordRepository.save(statusRecordEntity);
        return statusRecordMapper.toDTO(savedStatusRecord);
    }

    public StatusRecordDTO update(Long id, @Valid StatusRecordDTO statusRecordDTO) {
        StatusRecordEntity statusRecordEntity = statusRecordMapper.toEntity(statusRecordDTO);
        statusRecordEntity.setId(id);
        StatusRecordEntity updatedEntity = statusRecordRepository.save(statusRecordEntity);
        return statusRecordMapper.toDTO(updatedEntity);
    }

    public void delete(Long id) {
        statusRecordRepository.deleteById(id);
    }
}
