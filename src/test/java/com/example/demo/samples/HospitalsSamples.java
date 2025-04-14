package com.example.demo.samples;

import com.example.demo.controller.dto.HospitalDTO;
import com.example.demo.domain.Hospital;

public class HospitalsSamples {
    private final String id1 = "1111",
        name1 = "test1",
        address1 = "direccion 1",
        serviciosMedicos1 = "Servicios Medicos 1",

        id2 = "2222",
        nombre2 = "test2",
        direccion2 = "direccion 2",
        serviciosMedicos2 = "Servicios Medicos 2";

    public HospitalDTO hospitalDTOTest1(){
        HospitalDTO hospitalDTO = new HospitalDTO();
        hospitalDTO.setId(id1);
        hospitalDTO.setName(name1);
        hospitalDTO.setAddress(address1);
        hospitalDTO.setMedicalServices(serviciosMedicos1);
        return hospitalDTO;
    }
    public HospitalDTO HospitalDTOTest2(){
        HospitalDTO hospitalDTO = new HospitalDTO();
        hospitalDTO.setId(id2);
        hospitalDTO.setName(nombre2);
        hospitalDTO.setAddress(direccion2);
        hospitalDTO.setMedicalServices(serviciosMedicos2);
        return hospitalDTO;
    }
    public Hospital hospitalTest1(){
        Hospital hospital = new Hospital();
        hospital.setId(id1);
        hospital.setName(name1);
        hospital.setAddress(address1);
        hospital.setMedicalServices(serviciosMedicos1);
        hospital.setUser(new UserSamples().userTest1());
        return hospital;
    }

    public Hospital hospitalTest2(){
        Hospital hospital = new Hospital();
        hospital.setId(id2);
        hospital.setName(nombre2);
        hospital.setAddress(direccion2);
        hospital.setMedicalServices(serviciosMedicos2);
        hospital.setUser(new UserSamples().userTest2());
        return hospital;
    }
}
