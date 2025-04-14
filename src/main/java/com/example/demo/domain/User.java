package com.example.demo.domain;

import com.example.demo.config.Authorities;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {
    @Id
    private String id;
    @Column(unique=true)
    private String email;
    private String telephone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Authorities authority;
    private boolean activated;
    private String activationKey;
}