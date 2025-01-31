package com.orion.patient.service;

import com.orion.patient.dto.AppointmentDTO;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.mapper.AppointmentMapper;
import com.orion.patient.repository.AppointmentRepository;
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

    public AppointmentDTO update(Long id, @Valid AppointmentDTO appointmentDTO) {
        boolean isAvailable = isDoctorAvailable(
                appointmentDTO.doctorId(),
                appointmentDTO.clinicId(),
                appointmentDTO.appointmentTime()
        );

        if (!isAvailable) {
            throw new RuntimeException("Doctor is not available at the specified time.");
        }

        Optional<AppointmentEntity> existingAppointmentOpt = appointmentRepository.findById(id);
        if (!existingAppointmentOpt.isPresent()) {
            throw new RuntimeException("Appointment with ID " + id + " not found.");
        }

        AppointmentEntity existingAppointment = existingAppointmentOpt.get();
        existingAppointment.setDoctorId(appointmentDTO.doctorId());
        existingAppointment.setClinicId(appointmentDTO.clinicId());
        existingAppointment.setAppointmentTime(appointmentDTO.appointmentTime());

        AppointmentEntity updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentDTO> getAppointmentsForPatient(Long patientId) {
        List<AppointmentEntity> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean isDoctorAvailable(Long doctorId, Long clinicId, LocalDateTime appointmentTime) {
        return !appointmentRepository.existsByDoctorIdAndClinicIdAndAppointmentTime(
                doctorId, clinicId, appointmentTime
        );
    }

    public void createAppointment(AppointmentDTO appointmentDTO) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        appointmentRepository.save(appointmentEntity);
    }
}
