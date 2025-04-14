package com.example.demo.controller;

import com.example.demo.controller.dto.HospitalDTO;
import com.example.demo.domain.Hospital;
import com.example.demo.domain.User;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.samples.HospitalsSamples;
import com.example.demo.samples.UserSamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HospitalControllerIT {
    @Autowired
    private HospitalController hospitalController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    @DisplayName("Should create an Hospital and return OK")
    void createHospital() {
        User user = new UserSamples().userTest1();
        userRepository.save(user);

        HospitalDTO hospitalDTO = new HospitalsSamples().hospitalDTOTest1();

        ResponseEntity<Void> response = hospitalController.createHospital(hospitalDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Hospital hospital = hospitalRepository.findById(hospitalDTO.getId()).orElse(null);
        assertNotNull(hospital);
        assertEquals(hospitalDTO.getId(), hospital.getId());
        assertEquals(hospitalDTO.getName(), hospital.getName());
        assertEquals(hospitalDTO.getAddress(), hospital.getAddress());
        assertEquals(hospitalDTO.getMedicalServices(), hospital.getMedicalServices());
    }

    @Test
    @DisplayName("Should get all Users and return OK")
    void getAllHospitals() {
        Hospital hospital1 = new HospitalsSamples().hospitalTest1();
        Hospital hospital2 = new HospitalsSamples().hospitalTest2();

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital1);
        hospitals.add(hospital2);
        hospitalRepository.saveAll(hospitals);

        ResponseEntity<List<HospitalDTO>> listResponseEntity = hospitalController.getHospital();
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());

        List<HospitalDTO> hospitalDTOList = listResponseEntity.getBody();
        assertNotNull(hospitalDTOList);
        assertEquals(hospitals.size(), hospitalDTOList.size());
        assertTrue(hospitals.contains(hospital1));
        assertTrue(hospitals.contains(hospital2));
    }
}
