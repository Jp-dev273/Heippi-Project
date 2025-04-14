package com.example.demo.controller;

import com.example.demo.controller.dto.PhysicianDTO;
import com.example.demo.controller.mapper.PhysicianMapper;
import com.example.demo.domain.Physician;
import com.example.demo.domain.User;
import com.example.demo.repository.PhysicianRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.samples.MedicosSamples;
import com.example.demo.samples.UserSamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
public class PhysicianControllerTests {
    @Autowired
    private PhysicianController physicianController;
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhysicianMapper physicianMapper;

    @Test
    @DisplayName("Should create a Medico and return OK")
    public void createPhysician() {
        PhysicianDTO physicianDTO = new MedicosSamples().medicoDTOTest1();
        User user = new UserSamples().userTest1();
        userRepository.save(user);
        ResponseEntity<Void> response = physicianController.createPhysician(physicianDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Physician physician = physicianRepository.findById(physicianDTO.getId()).orElse(null);
        assertNotNull(physician);

        Optional<User> optionalUser = userRepository.findById(physician.getId());
        assertTrue(optionalUser.isPresent());

        User userUpdated = optionalUser.get();
        assertEquals(physicianDTO.getName(), physician.getName());
        assertEquals(physicianDTO.getAddress(), physician.getAddress());
        assertTrue(passwordEncoder.matches(physicianDTO.getNewPassword(), userUpdated.getPassword()));

    }

    @Test
    @DisplayName("Should get all Medicos created and return OK")
    public void getAllMedicos() {
        Physician physician1 = new MedicosSamples().medicoTest1();
        Physician physician2 = new MedicosSamples().medicoTest2();
        List<Physician> physicians = Arrays.asList(physician1, physician2);
        physicianRepository.saveAll(physicians);

        ResponseEntity<List<PhysicianDTO>> response = physicianController.getAllPhysicians();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<PhysicianDTO> physicianDTOList = response.getBody();
        assertNotNull(physicianDTOList);
        assertEquals(physicianDTOList.size(), physicians.size());

        Physician retrievedPhysician1 = physicianMapper.physicianDTOToPhysician(physicianDTOList.get(0));
        Physician retrievedPhysician2 = physicianMapper.physicianDTOToPhysician(physicianDTOList.get(1));

        assertEquals(physician1.getId(), retrievedPhysician1.getId());
        assertEquals(physician1.getName(), retrievedPhysician1.getName());
        assertEquals(physician1.getAddress(), retrievedPhysician1.getAddress());

        assertEquals(physician2.getId(), retrievedPhysician2.getId());
        assertEquals(physician2.getName(), retrievedPhysician2.getName());
        assertEquals(physician2.getAddress(), retrievedPhysician2.getAddress());
    }
}
