package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity(name = "Token_Password")
public class Recovery {
    @Id
    private String id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    private String token;
    @Temporal(TemporalType.DATE)
    private Date expiration;

}
