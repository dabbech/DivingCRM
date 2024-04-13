package com.example.demo.sessions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface sessionsRepository extends JpaRepository<sessions, UUID> {
}
