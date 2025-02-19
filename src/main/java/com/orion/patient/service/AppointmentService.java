package com.orion.patient.service;

import com.orion.patient.dto.AppointmentDto;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.mapper.AppointmentMapper;
import com.orion.patient.repository.AppointmentRepository;
import com.orion.patient.util.exception.PatientApplicationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDto> findAll() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AppointmentDto> findById(Long id) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(id);
        return appointmentEntity.map(appointmentMapper::toDto);
    }

    public AppointmentDto save(AppointmentDto appointmentDTO) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);
        return appointmentMapper.toDto(savedAppointment);
    }

    public AppointmentDto update(Long id, @Valid AppointmentDto appointmentDTO) {
        boolean isAvailable = isDoctorNotAvailable(
                appointmentDTO.doctorId(),
                appointmentDTO.clinicId(),
                appointmentDTO.appointmentTime()
        );

        if (!isAvailable) {
            throw new PatientApplicationException("Doctor is not available at the specified time.");
        }

        Optional<AppointmentEntity> existingAppointmentOpt = appointmentRepository.findById(id);
        if (existingAppointmentOpt.isEmpty()) {
            throw new PatientApplicationException("Appointment with ID " + id + " not found.");
        }

        AppointmentEntity existingAppointment = existingAppointmentOpt.get();
        existingAppointment.setDoctorId(appointmentDTO.doctorId());
        existingAppointment.setClinicId(appointmentDTO.clinicId());
        existingAppointment.setAppointmentTime(appointmentDTO.appointmentTime());

        AppointmentEntity updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDto(updatedAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentDto> getAppointmentsForPatient(Long patientId) {
        List<AppointmentEntity> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean isDoctorNotAvailable(Long doctorId, Long clinicId, LocalDateTime appointmentTime) {
        return appointmentRepository.existsByDoctorIdAndClinicIdAndAppointmentTime(
                doctorId, clinicId, appointmentTime
        );
    }

    public void createAppointment(AppointmentDto appointmentDTO) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        appointmentRepository.save(appointmentEntity);
    }
}
