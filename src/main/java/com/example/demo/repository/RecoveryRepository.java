package com.example.demo.repository;

import com.example.demo.domain.Recovery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecoveryRepository extends JpaRepository<Recovery,String> {
    Recovery findByToken(String token);
}
