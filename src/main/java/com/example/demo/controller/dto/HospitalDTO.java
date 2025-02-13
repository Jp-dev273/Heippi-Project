package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class HospitalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private String address;
    @NotBlank(message = "medicalServices is mandatory")
    private String medicalServices;

}
