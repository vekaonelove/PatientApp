package com.orion.patient.service;

import com.orion.patient.dto.EmergencyContactDTO;
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

    public List<EmergencyContactDTO> findAll() {
        List<EmergencyContactEntity> emergencyContacts = emergencyContactRepository.findAll();
        return emergencyContacts.stream()
                .map(emergencyContactMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmergencyContactDTO> findById(Long id) {
        Optional<EmergencyContactEntity> emergencyContactEntity = emergencyContactRepository.findById(id);
        return emergencyContactEntity.map(emergencyContactMapper::toDTO);
    }

    public EmergencyContactDTO save(EmergencyContactDTO emergencyContactDTO) {
        EmergencyContactEntity emergencyContactEntity = emergencyContactMapper.toEntity(emergencyContactDTO);
        EmergencyContactEntity savedEntity = emergencyContactRepository.save(emergencyContactEntity);
        return emergencyContactMapper.toDTO(savedEntity);
    }

    public EmergencyContactDTO update(Long id, @Valid EmergencyContactDTO emergencyContactDTO) {
        if (!emergencyContactRepository.existsById(id)) {
            return null;
        }
        EmergencyContactEntity emergencyContactEntity = emergencyContactMapper.toEntity(emergencyContactDTO);
        emergencyContactEntity.setId(id);
        EmergencyContactEntity updatedEntity = emergencyContactRepository.save(emergencyContactEntity);
        return emergencyContactMapper.toDTO(updatedEntity);
    }

    public void delete(Long id) {
        emergencyContactRepository.deleteById(id);
    }
}
