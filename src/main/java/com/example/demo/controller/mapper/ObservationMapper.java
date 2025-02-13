package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.ObservationDTO;
import com.example.demo.domain.Hospital;
import com.example.demo.domain.Physician;
import com.example.demo.domain.Observation;
import com.example.demo.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationMapper {
    public Observation observationDTOtoObservation(ObservationDTO observationDTO) {
        Observation observation = new Observation();

        Patient patient = new Patient();
        patient.setId(observationDTO.getIdPatient());
        observation.setPatient(patient);

        Physician physician = new Physician();
        physician.setId(observationDTO.getIdPhysician());
        observation.setPhysician(physician);

        Hospital hospital = new Hospital();
        hospital.setId(observationDTO.getIdHospital());
        observation.setHospital(hospital);

        observation.setDescription(observationDTO.getDescription());
        observation.setHealthStatus(observationDTO.getHealthState());
        observation.setSpecialty(observationDTO.getSpecialty());
        return observation;
    }

    public ObservationDTO observationToObservationDTO(Observation observation) {
        ObservationDTO observationDTO = new ObservationDTO();
        observationDTO.setIdPatient(observation.getPatient().getId());
        observationDTO.setIdPhysician(observation.getPhysician().getId());
        observationDTO.setIdHospital(observation.getHospital().getId());
        observationDTO.setDescription(observation.getDescription());
        observationDTO.setHealthState(observation.getHealthStatus());
        observationDTO.setSpecialty(observation.getSpecialty());
        return observationDTO;
    }

    public List<ObservationDTO> observationsToObservationsDTOs(List<Observation> all) {
        List<ObservationDTO> observationDTOS = new ArrayList<>();
        for (Observation observation : all) {
            observationDTOS.add(observationToObservationDTO(observation));
        }
        return observationDTOS;
    }
}
