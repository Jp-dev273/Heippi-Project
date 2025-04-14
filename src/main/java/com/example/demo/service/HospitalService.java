package com.example.demo.service;

import com.example.demo.controller.dto.HospitalDTO;
import com.example.demo.controller.mapper.HospitalMapper;
import com.example.demo.domain.Hospital;
import com.example.demo.domain.User;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HospitalService {
    private final HospitalMapper hospitalMapper;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public HospitalService(HospitalRepository hospitalRepository, UserRepository userRepository) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = new HospitalMapper();
        this.userRepository = userRepository;
    }

    public void createHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalMapper.hospitalDTOtoHospital(hospitalDTO);
        Optional<User> user = userRepository.findById(hospital.getId());
        if (user.isEmpty()) {
            return;
        }
        hospital.setUser(user.get());
        hospitalRepository.save(hospital);
    }

    public List<HospitalDTO> getListHospitals() {
        return hospitalMapper.hospitalsToHospitalsDTO(hospitalRepository.findAll());
    }
}
