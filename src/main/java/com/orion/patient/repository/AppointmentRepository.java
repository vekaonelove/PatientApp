package com.orion.patient.repository;

import com.orion.patient.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatientId(Long patientId);
    boolean existsByDoctorIdAndClinicIdAndAppointmentTime(
            Long doctorId,
            Long clinicId,
            LocalDateTime appointmentTime
    );

}
