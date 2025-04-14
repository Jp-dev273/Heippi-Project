package com.example.demo.controller.dto;

import com.example.demo.config.Authorities;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    @Email
    @NotBlank(message = "email is mandatory")
    private String email;
    private String telephone;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotNull
    private Authorities authority;

}
