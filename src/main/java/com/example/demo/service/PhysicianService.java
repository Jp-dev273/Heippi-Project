package com.example.demo.service;

import com.example.demo.controller.dto.PhysicianDTO;
import com.example.demo.controller.mapper.PhysicianMapper;
import com.example.demo.domain.Physician;
import com.example.demo.domain.User;
import com.example.demo.repository.PhysicianRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhysicianService {
    private final PhysicianRepository physicianRepository;
    private final PhysicianMapper physicianMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PhysicianService(PhysicianRepository physicianRepository, PhysicianMapper physicianMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.physicianRepository = physicianRepository;
        this.physicianMapper = physicianMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void createPhysician(PhysicianDTO physicianDTO) {
        Physician physician = physicianMapper.physicianDTOToPhysician(physicianDTO);
        Optional<User> optionalUser = userRepository.findById(physicianDTO.getId());
        if (optionalUser.isEmpty()) {
            return;
        }
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(physicianDTO.getNewPassword()));
        physician.setUser(user);
        physicianRepository.save(physician);
    }

    public List<PhysicianDTO> getAllPhysicians() {
        return physicianMapper.physiciansToPhysiciansDTO(physicianRepository.findAll());
    }
}
