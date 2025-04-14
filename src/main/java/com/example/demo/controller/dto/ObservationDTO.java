package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ObservationDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;
    @NotBlank(message = "patient is mandatory")
    private String idPatient;
    @NotBlank(message = "physician is mandatory")
    private String idPhysician;
    @NotBlank(message = "hospital is mandatory")
    private String idHospital;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "healthState is mandatory")
    private String healthState;
    @NotBlank(message = "specialty is mandatory")
    private String specialty;
}
