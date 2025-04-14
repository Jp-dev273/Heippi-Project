package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.PhysicianDTO;
import com.example.demo.domain.Physician;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhysicianMapper {
    public Physician physicianDTOToPhysician(PhysicianDTO physicianDTO) {
        Physician physician = new Physician();
        physician.setId(physicianDTO.getId());
        physician.setName(physicianDTO.getName());
        physician.setAddress( physicianDTO.getAddress());
        return physician;
    }

    public PhysicianDTO physicianToPhysicianDTO(Physician physician) {
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setId(physician.getId());
        physicianDTO.setName(physician.getName());
        physicianDTO.setAddress(physician.getAddress());
        return physicianDTO;
    }

    public List<PhysicianDTO> physiciansToPhysiciansDTO(Iterable<Physician> all) {
        List<PhysicianDTO> physiciansDTOs = new ArrayList<>();
        for (Physician physician : all) {
            physiciansDTOs.add(physicianToPhysicianDTO(physician));
        }
        return physiciansDTOs;
    }
}
