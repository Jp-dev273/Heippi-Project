package com.example.demo.samples;

import com.example.demo.controller.dto.PatientDTO;
import com.example.demo.domain.Patient;

import java.sql.Date;

public class PatientSamples {
    private final String id1 = "1111",
        name1 = "paciente1",
        address1 = "direccion 1",
        fechaNacimiento1 = "2000-01-01",

        id2 = "2222",
        nombre2 = "paciente2",
        direccion2 = "direccion2",
        getFechaNacimiento2 = "2000-01-01";

    public Patient pacienteTest1(){
        Patient patient = new Patient();
        patient.setId(id1);
        patient.setName(name1);
        patient.setAddress(address1);
        patient.setBirthDate(Date.valueOf(fechaNacimiento1));
        patient.setUser(new UserSamples().userTest1());
        return patient;
    }

    public Patient pacienteTest2(){
        Patient patient = new Patient();
        patient.setId(id2);
        patient.setName(nombre2);
        patient.setAddress(direccion2);
        patient.setBirthDate(Date.valueOf(getFechaNacimiento2));
        patient.setUser(new UserSamples().userTest2());
        return patient;
    }

    public PatientDTO pacienteDTOTest1(){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(id1);
        patientDTO.setName(name1);
        patientDTO.setAddress(address1);
        patientDTO.setBirthDate(Date.valueOf(fechaNacimiento1));
        return patientDTO;
    }

    public PatientDTO pacienteDTOTest2(){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(id2);
        patientDTO.setName(nombre2);
        patientDTO.setAddress(direccion2);
        patientDTO.setBirthDate(Date.valueOf(getFechaNacimiento2));
        return patientDTO;
    }
}
