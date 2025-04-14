package com.example.demo.samples;

import com.example.demo.controller.dto.PhysicianDTO;
import com.example.demo.domain.Physician;
import com.example.demo.domain.User;

public class MedicosSamples {
    private final
        String nombre1 = "medico1",
        direccion1 = "direccion1",

        nombre2 = "medico2",
        direccion2 = "direccion2";

    public Physician medicoTest1(){
        User userTest1 = new UserSamples().userTest1();
        Physician physician = new Physician();
        physician.setId(userTest1.getId());
        physician.setName(nombre1);
        physician.setAddress(direccion1);
        physician.setUser(userTest1);
        return physician;
    }

    public Physician medicoTest2(){
        User userTest2 = new UserSamples().userTest2();
        Physician physician = new Physician();
        physician.setId(userTest2.getId());
        physician.setName(nombre2);
        physician.setAddress(direccion2);
        physician.setUser(userTest2);
        return physician;
    }

    public PhysicianDTO medicoDTOTest1(){
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setId(new UserSamples().userTest1().getId());
        physicianDTO.setName(nombre1);
        physicianDTO.setAddress(direccion1);
        physicianDTO.setNewPassword("123456");
        return physicianDTO;
    }

    public PhysicianDTO medicoDTOTest2(){
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setId(new UserSamples().userTest2().getId());
        physicianDTO.setName(nombre2);
        physicianDTO.setAddress(direccion2);
        physicianDTO.setNewPassword("1234567");
        return physicianDTO;
    }
}
