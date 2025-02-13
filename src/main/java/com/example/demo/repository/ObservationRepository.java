package com.example.demo.repository;

import com.example.demo.domain.Observation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObservationRepository extends CrudRepository<Observation, Long> {
    List<Observation> findAllByPatientId(String patientId);

    List<Observation> findAllByPhysicianId(String s);

    List<Observation> findAllByHospitalId(String id);
}
