package com.example.demo.controller;

import com.example.demo.controller.dto.PatientDTO;
import com.example.demo.controller.mapper.PatientMapper;
import com.example.demo.domain.Patient;
import com.example.demo.domain.User;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.samples.PatientSamples;
import com.example.demo.samples.UserSamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PatientControllerTests {
    @Autowired
    private PatientController patientController;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientMapper patientMapper;

    @Test
    @DisplayName("Should create a Paciente and return OK")
    public void createPaciente() {
        User user = new UserSamples().userTest1();
        userRepository.save(user);

        PatientDTO patientDTO = new PatientSamples().pacienteDTOTest1();
        ResponseEntity<Void> response = patientController.createPatient(patientDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Patient patient = patientRepository.findById(patientDTO.getId()).orElse(null);
        assertNotNull(patient);
        assertEquals(patientDTO.getId(), patient.getId());
        assertEquals(patientDTO.getName(), patient.getName());
        assertEquals(patientDTO.getAddress(), patient.getAddress());
        assertEquals(patientDTO.getBirthDate(), patient.getBirthDate());
    }

    @Test
    @DisplayName("Should get all Pacientes and return OK")
    public void getAllPacientes() {
        Patient patient1 = new PatientSamples().pacienteTest1();
        Patient patient2 = new PatientSamples().pacienteTest2();
        List<Patient> patients = Arrays.asList(patient1, patient2);
        patientRepository.saveAll(patients);

        ResponseEntity<List<PatientDTO>> response = patientController.getPatients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<PatientDTO> patientDTOS = response.getBody();
        assertNotNull(patientDTOS);
        assertEquals(patientDTOS.size(), patients.size());

        PatientDTO paciente1DTO = patientMapper.patientToPatientDTO(patient1);
        PatientDTO paciente2DTO = patientMapper.patientToPatientDTO(patient2);

        assertEquals(paciente1DTO, patientDTOS.get(0));
        assertEquals(paciente2DTO, patientDTOS.get(1));
    }
}
