package com.orion.patient.service;

import com.orion.patient.dto.EmergencyContactDto;
import com.orion.patient.entity.EmergencyContactEntity;
import com.orion.patient.mapper.EmergencyContactMapper;
import com.orion.patient.repository.EmergencyContactRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmergencyContactService {

    private final EmergencyContactRepository emergencyContactRepository;
    private final EmergencyContactMapper emergencyContactMapper;

    public EmergencyContactService(EmergencyContactRepository emergencyContactRepository, EmergencyContactMapper emergencyContactMapper) {
        this.emergencyContactRepository = emergencyContactRepository;
        this.emergencyContactMapper = emergencyContactMapper;
    }

    public List<EmergencyContactDto> findAll() {
        List<EmergencyContactEntity> emergencyContacts = emergencyContactRepository.findAll();
        return emergencyContacts.stream()
                .map(emergencyContactMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EmergencyContactDto> findById(Long id) {
        Optional<EmergencyContactEntity> emergencyContactEntity = emergencyContactRepository.findById(id);
        return emergencyContactEntity.map(emergencyContactMapper::toDto);
    }

    public EmergencyContactDto save(EmergencyContactDto emergencyContactDTO) {
        EmergencyContactEntity emergencyContactEntity = emergencyContactMapper.toEntity(emergencyContactDTO);
        EmergencyContactEntity savedEntity = emergencyContactRepository.save(emergencyContactEntity);
        return emergencyContactMapper.toDto(savedEntity);
    }

    public EmergencyContactDto update(Long id, @Valid EmergencyContactDto emergencyContactDTO) {
        if (!emergencyContactRepository.existsById(id)) {
            return null;
        }
        EmergencyContactEntity emergencyContactEntity = emergencyContactMapper.toEntity(emergencyContactDTO);
        emergencyContactEntity.setId(id);
        EmergencyContactEntity updatedEntity = emergencyContactRepository.save(emergencyContactEntity);
        return emergencyContactMapper.toDto(updatedEntity);
    }

    public void delete(Long id) {
        emergencyContactRepository.deleteById(id);
    }
}
