package com.example.demo.DiveType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiveTypeRepository extends JpaRepository<DiveType, UUID> {
}
