package com.example.demo.samples;

import com.example.demo.controller.dto.ObservationDTO;
import com.example.demo.domain.Observation;

public class ObservacionsSamples {
    private final
        String idPaciente1 = new PatientSamples().pacienteTest1().getId(),
        idMedico1 = new MedicosSamples().medicoTest1().getId(),
        idHospital1 = new HospitalsSamples().hospitalTest1().getId(),
        descripcion1 = "descripcion 1",
        estadoSalud1 = "estado salud 1",
        especialidad1 = "especialidad 1",

        idPaciente2 = new PatientSamples().pacienteTest2().getId(),
        idMedico2 = new MedicosSamples().medicoTest2().getId(),
        idHospital2 = new HospitalsSamples().hospitalTest2().getId(),
        descripcion2 = "descripcion 2",
        estadoSalud2 = "estado salud 2",
        especialidad2 = "especialidad 2";

    public ObservationDTO observacionDTOTest1(){
        ObservationDTO observationDTO = new ObservationDTO();
        observationDTO.setIdPatient(idPaciente1);
        observationDTO.setIdPhysician(idMedico1);
        observationDTO.setIdHospital(idHospital1);
        observationDTO.setDescription(descripcion1);
        observationDTO.setHealthState(estadoSalud1);
        observationDTO.setSpecialty(especialidad1);
        return observationDTO;
    }

    public ObservationDTO observacionDTOTest2(){
        ObservationDTO observationDTO = new ObservationDTO();
        observationDTO.setIdPatient(idPaciente2);
        observationDTO.setIdPhysician(idMedico2);
        observationDTO.setIdHospital(idHospital2);
        observationDTO.setDescription(descripcion2);
        observationDTO.setHealthState(estadoSalud2);
        observationDTO.setSpecialty(especialidad2);
        return observationDTO;
    }

    public Observation observacionTest1(){
        Observation observation = new Observation();
        observation.setPatient(new PatientSamples().pacienteTest1());
        observation.setPhysician(new MedicosSamples().medicoTest1());
        observation.setHospital(new HospitalsSamples().hospitalTest1());
        observation.setDescription(descripcion1);
        observation.setHealthStatus(estadoSalud1);
        observation.setSpecialty(especialidad1);
        return observation;
    }

    public Observation observacionTest2(){
        Observation observation = new Observation();
        observation.setPatient(new PatientSamples().pacienteTest2());
        observation.setPhysician(new MedicosSamples().medicoTest2());
        observation.setHospital(new HospitalsSamples().hospitalTest2());
        observation.setDescription(descripcion2);
        observation.setHealthStatus(estadoSalud2);
        observation.setSpecialty(especialidad2);
        return observation;
    }
}
