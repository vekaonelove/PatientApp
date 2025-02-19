package com.orion.patient.service;

import com.orion.patient.dto.StatusRecordDto;
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

    public List<StatusRecordDto> findAll() {
        List<StatusRecordEntity> statusRecords = statusRecordRepository.findAll();
        return statusRecords.stream().map(statusRecordMapper::toDto).collect(Collectors.toList());
    }

    public Optional<StatusRecordDto> findById(Long id) {
        Optional<StatusRecordEntity> statusRecordEntity = statusRecordRepository.findById(id);
        return statusRecordEntity.map(statusRecordMapper::toDto);
    }

    public StatusRecordDto save(StatusRecordDto statusRecordDTO) {
        StatusRecordEntity statusRecordEntity = statusRecordMapper.toEntity(statusRecordDTO);
        StatusRecordEntity savedStatusRecord = statusRecordRepository.save(statusRecordEntity);
        return statusRecordMapper.toDto(savedStatusRecord);
    }

    public StatusRecordDto update(Long id, @Valid StatusRecordDto statusRecordDTO) {
        StatusRecordEntity statusRecordEntity = statusRecordMapper.toEntity(statusRecordDTO);
        statusRecordEntity.setId(id);
        StatusRecordEntity updatedEntity = statusRecordRepository.save(statusRecordEntity);
        return statusRecordMapper.toDto(updatedEntity);
    }

    public void delete(Long id) {
        statusRecordRepository.deleteById(id);
    }
}
