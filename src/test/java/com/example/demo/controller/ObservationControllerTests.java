package com.example.demo.controller;

import com.example.demo.controller.dto.ObservationDTO;
import com.example.demo.controller.mapper.ObservationMapper;
import com.example.demo.domain.Hospital;
import com.example.demo.domain.Physician;
import com.example.demo.domain.Observation;
import com.example.demo.domain.Patient;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.PhysicianRepository;
import com.example.demo.repository.ObservationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.samples.HospitalsSamples;
import com.example.demo.samples.MedicosSamples;
import com.example.demo.samples.ObservacionsSamples;
import com.example.demo.samples.PatientSamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ObservationControllerTests {
    @Autowired
    private ObservationController observationController;
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private ObservationMapper observationMapper;

    @Test
    @DisplayName("Should create an Observation and return OK")
    public void createObservation() {
        Hospital hospital = new HospitalsSamples().hospitalTest1();
        hospitalRepository.save(hospital);

        Physician physician = new MedicosSamples().medicoTest1();
        physicianRepository.save(physician);

        Patient patient = new PatientSamples().pacienteTest1();
        patientRepository.save(patient);

        ObservationDTO observationDTO = new ObservacionsSamples().observacionDTOTest1();
        ResponseEntity<Void> response = observationController.createMedicalObservation(observationDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Observation> observationList = observationRepository.findAllByPatientId(observationDTO.getIdPatient());
        assertNotNull(observationList);
        assertEquals(1, observationList.size());

        Observation observation = observationList.get(0);
        assertEquals(observationDTO.getIdHospital(), observation.getHospital().getId());
        assertEquals(observationDTO.getIdPhysician(), observation.getPhysician().getId());
        assertEquals(observationDTO.getIdPatient(), observation.getPatient().getId());
        assertEquals(observationDTO.getDescription(), observation.getDescription());
        assertEquals(observationDTO.getHealthState(), observation.getHealthStatus());
        assertEquals(observationDTO.getSpecialty(), observation.getSpecialty());
    }

    @Test
    @DisplayName("Should get all observaciones created and return OK")
    public void getAllObservaciones() {
        Hospital hospital1 = new HospitalsSamples().hospitalTest1();
        Hospital hospital2 = new HospitalsSamples().hospitalTest2();
        List<Hospital> hospitalList = Arrays.asList(hospital1, hospital2);
        hospitalRepository.saveAll(hospitalList);

        Physician physician1 = new MedicosSamples().medicoTest1();
        Physician physician2 = new MedicosSamples().medicoTest2();
        List<Physician> physicianList = Arrays.asList(physician1, physician2);
        physicianRepository.saveAll(physicianList);

        Patient patient1 = new PatientSamples().pacienteTest1();
        Patient patient2 = new PatientSamples().pacienteTest2();
        List<Patient> patientList = Arrays.asList(patient1, patient2);
        patientRepository.saveAll(patientList);

        Observation observation1 = new ObservacionsSamples().observacionTest1();
        Observation observation2 = new ObservacionsSamples().observacionTest2();
        List<Observation> observationList = Arrays.asList(observation1, observation2);
        observationRepository.saveAll(observationList);

        Authentication authentication = mock(Authentication.class);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("HOSPITAL");
        when(authentication.getName()).thenReturn(hospital1.getId());
        when(authentication.getAuthorities()).thenAnswer(invocation -> Collections.singletonList(grantedAuthority));

        ResponseEntity<List<ObservationDTO>> response1 = observationController.getMedicalObservations(authentication);
        assertEquals(HttpStatus.OK, response1.getStatusCode());

        List<ObservationDTO> observationDTOList = response1.getBody();
        assertNotNull(observationDTOList);
        assertEquals(1, observationDTOList.size());
        ObservationDTO observationDTO1 = observationMapper.observationToObservationDTO(observation1);
        assertTrue(observationDTOList.contains(observationDTO1));

        when(authentication.getName()).thenReturn(hospital2.getId());
        when(authentication.getAuthorities()).thenAnswer(invocation -> Collections.singletonList(grantedAuthority));

        ResponseEntity<List<ObservationDTO>> response2 = observationController.getMedicalObservations(authentication);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        observationDTOList = response2.getBody();
        assertNotNull(observationDTOList);
        assertEquals(1, observationDTOList.size());
        ObservationDTO observationDTO2 = observationMapper.observationToObservationDTO(observation2);
        assertTrue(observationDTOList.contains(observationDTO2));

    }
}
