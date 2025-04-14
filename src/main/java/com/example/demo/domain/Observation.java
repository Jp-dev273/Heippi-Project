package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Observation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "id_physician")
    private Physician physician;
    @ManyToOne
    @JoinColumn(name = "id_hospital")
    private Hospital hospital;
    private String description;
    private String healthStatus;
    private String specialty;
}
