package com.example.demo.DiveType;

import com.example.demo.Agents.Agents;
import com.example.demo.Club.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiveTypeRepository extends JpaRepository<DiveType, UUID> {
    List<DiveType> findByClub(Club club);
}
