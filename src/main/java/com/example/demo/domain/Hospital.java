package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Hospital {
    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_hospital")
    private User user;
    private String name;
    private String address;
    private String medicalServices;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private List<Physician> physicians;
}
