package com.example.demo.Agents;

import com.example.demo.Club.Club;
import com.example.demo.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgentsRepository extends JpaRepository<Agents, UUID> {
    List<Agents> findByClub(Club club);
}
