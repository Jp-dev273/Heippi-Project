package com.example.demo.controller;

import com.example.demo.controller.dto.PhysicianDTO;
import com.example.demo.service.PhysicianService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {
    private final PhysicianService physicianService;

    public PhysicianController(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    @GetMapping
    public ResponseEntity<List<PhysicianDTO>> getAllPhysicians() {
        return ResponseEntity.ok(
                physicianService.getAllPhysicians()
        );
    }
    @PostMapping
    public ResponseEntity<Void> createPhysician(@Valid @RequestBody PhysicianDTO physicianDTO) {
        physicianService.createPhysician(physicianDTO);
        return ResponseEntity.ok().build();
    }
}
