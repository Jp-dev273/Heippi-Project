package com.example.demo.repository;

import com.example.demo.domain.Physician;
import org.springframework.data.repository.CrudRepository;

public interface PhysicianRepository extends CrudRepository<Physician, String> {
}
