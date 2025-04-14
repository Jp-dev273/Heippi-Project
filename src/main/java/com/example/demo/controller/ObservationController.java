package com.example.demo.controller;

import com.example.demo.controller.dto.ObservationDTO;
import com.example.demo.service.ObservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observations")
public class ObservationController {
    private final ObservationService observationService;
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @GetMapping
    public ResponseEntity<List<ObservationDTO>> getMedicalObservations(Authentication authentication) {
        return ResponseEntity.ok().body(observationService.getMedicalObservations(authentication));
    }
    @PostMapping
    public ResponseEntity<Void> createMedicalObservation(@Valid @RequestBody ObservationDTO observationDTO) {
        observationService.createMedicalObservation(observationDTO);
        return ResponseEntity.ok().build();
    }
}
