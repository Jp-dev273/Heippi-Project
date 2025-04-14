package com.example.demo.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EmailUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
}
