package com.example.demo.controller;

import com.example.demo.controller.dto.PatientDTO;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<Void> createPatient(@Valid @RequestBody PatientDTO patientDTO){
        patientService.createPatient(patientDTO);
        return ResponseEntity.ok().build();
    }
}
