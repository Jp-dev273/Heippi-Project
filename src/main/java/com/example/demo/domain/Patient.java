package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Patient {
    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private User user;
    private String name;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
