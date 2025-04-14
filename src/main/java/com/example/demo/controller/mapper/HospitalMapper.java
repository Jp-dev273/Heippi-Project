package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.HospitalDTO;
import com.example.demo.domain.Hospital;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalMapper {
    public Hospital hospitalDTOtoHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        hospital.setId(hospitalDTO.getId());
        hospital.setName(hospitalDTO.getName());
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setMedicalServices(hospitalDTO.getMedicalServices());
        return hospital;
    }

    public HospitalDTO hospitalToHospitalDTO(Hospital hospital) {
        HospitalDTO hospitalDTO = new HospitalDTO();
        hospitalDTO.setId(hospital.getId());
        hospitalDTO.setName(hospital.getName());
        hospitalDTO.setAddress(hospital.getAddress());
        hospitalDTO.setMedicalServices(hospital.getMedicalServices());
        return hospitalDTO;
    }

    public List<HospitalDTO> hospitalsToHospitalsDTO(Iterable<Hospital> all) {
        List<HospitalDTO> hospitalDTOS = new ArrayList<>();
        for (Hospital hospital : all) {
            hospitalDTOS.add(hospitalToHospitalDTO(hospital));
        }
        return hospitalDTOS;
    }
}
