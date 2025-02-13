package com.example.demo.service;

import com.example.demo.controller.dto.ObservationDTO;
import com.example.demo.controller.mapper.ObservationMapper;
import com.example.demo.repository.ObservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ObservationService {
    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;

    public ObservationService(ObservationRepository observationRepository, ObservationMapper observationMapper) {
        this.observationRepository = observationRepository;
        this.observationMapper = observationMapper;
    }
    public List<ObservationDTO> getMedicalObservations(Authentication authentication) {
        //Process the Collection of Authorities and get the first element
        GrantedAuthority grantedAuthority = authentication
                .getAuthorities().iterator().next();

        String id = authentication.getName();
        switch (grantedAuthority.getAuthority()) {
            case "HOSPITAL"-> {
                return observationMapper.observationsToObservationsDTOs(observationRepository.findAllByHospitalId(id));
            }
            case "MEDICO" -> {
                return observationMapper.observationsToObservationsDTOs(observationRepository.findAllByPhysicianId(id));
            }
            case "PACIENTE" -> {
                return observationMapper.observationsToObservationsDTOs(observationRepository.findAllByPatientId(id));
            }
            default -> {return null;}
        }
    }

    public void createMedicalObservation(ObservationDTO observationDTO) {
        observationRepository.save(observationMapper.observationDTOtoObservation(observationDTO));
    }
}
