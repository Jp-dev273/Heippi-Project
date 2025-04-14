package com.example.demo.service;

import com.example.demo.controller.dto.PatientDTO;
import com.example.demo.controller.mapper.PatientMapper;
import com.example.demo.domain.Patient;
import com.example.demo.domain.User;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userRepository = userRepository;
    }
    public void createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.patientDTOToPatient(patientDTO);
        Optional<User> optionalUser = userRepository.findById(patient.getId());
        if (optionalUser.isEmpty()){
            return;
        }
        patient.setUser(optionalUser.get());
        patientRepository.save(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientMapper.patientsToPatientsDTO(patientRepository.findAll());
    }
}
