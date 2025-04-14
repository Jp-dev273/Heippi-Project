package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.PatientDTO;
import com.example.demo.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientMapper {
    public Patient patientDTOToPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setBirthDate(patientDTO.getBirthDate());
        return patient;
    }
    public PatientDTO patientToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setBirthDate(patient.getBirthDate());
        return patientDTO;
    }

    public List<PatientDTO> patientsToPatientsDTO(Iterable<Patient> all) {
        List<PatientDTO> patientsDTO = new ArrayList<>();
        for (Patient patient : all) {
            patientsDTO.add(patientToPatientDTO(patient));
        }
        return patientsDTO;
    }
}
