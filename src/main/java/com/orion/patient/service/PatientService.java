package com.orion.patient.service;

import com.orion.patient.dao.PatientDAO;
import com.orion.patient.dto.PatientDTO;
import com.orion.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Get all patients
    public List<PatientDTO> getAllPatients() {
        List<PatientDAO> patients = patientRepository.findAll();

        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a patient by ID
    public Optional<PatientDTO> getPatientById(Long id) {
        Optional<PatientDAO> patientDAO = patientRepository.findById(id);
        return patientDAO.map(this::convertToDTO);
    }

    // Create a new patient
    public PatientDTO createPatient(PatientDTO patientDTO) {
        // Convert DTO to DAO to save
        PatientDAO patientDAO = convertToDAO(patientDTO);
        PatientDAO savedPatient = patientRepository.save(patientDAO);
        // Convert saved DAO back to DTO
        return convertToDTO(savedPatient);
    }

    // Convert DAO to DTO
    private PatientDTO convertToDTO(PatientDAO patientDAO) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patientDAO.getId());
        patientDTO.setFirstName(patientDAO.getFirstName());
        patientDTO.setLastName(patientDAO.getLastName());
        patientDTO.setBirthDate(patientDAO.getBirthDate());
        patientDTO.setGender(patientDAO.getGender());
        patientDTO.setPhoneNumber(patientDAO.getPhoneNumber());
        patientDTO.setSsn(patientDAO.getSsn());
        patientDTO.setCountryName(patientDAO.getCountryName());
        patientDTO.setCityId(patientDAO.getCityId());
        patientDTO.setAddress(patientDAO.getAddress());
        patientDTO.setContactId(patientDAO.getContactId());
        return patientDTO;
    }

    // Convert DTO to DAO
    private PatientDAO convertToDAO(PatientDTO patientDTO) {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setFirstName(patientDTO.getFirstName());
        patientDAO.setLastName(patientDTO.getLastName());
        patientDAO.setBirthDate(patientDTO.getBirthDate());
        patientDAO.setGender(patientDTO.getGender());
        patientDAO.setPhoneNumber(patientDTO.getPhoneNumber());
        patientDAO.setSsn(patientDTO.getSsn());
        patientDAO.setCountryName(patientDTO.getCountryName());
        patientDAO.setCityId(patientDTO.getCityId());
        patientDAO.setAddress(patientDTO.getAddress());
        patientDAO.setContactId(patientDTO.getContactId());
        return patientDAO;
    }
}
