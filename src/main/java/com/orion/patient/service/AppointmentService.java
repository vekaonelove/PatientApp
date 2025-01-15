package com.orion.patient.service;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.mapper.AppointmentMapper;
import com.orion.patient.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDTO> findAll() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AppointmentDTO> findById(Long id) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(id);
        return appointmentEntity.map(appointmentMapper::toDTO);
    }

    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);
        return appointmentMapper.toDTO(savedAppointment);
    }

    public AppointmentDTO update(Long id, AppointmentDTO appointmentDTO) {
        if (!appointmentRepository.existsById(id)) {
            return null;
        }
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        appointmentEntity.setId(id);
        AppointmentEntity updatedAppointment = appointmentRepository.save(appointmentEntity);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
