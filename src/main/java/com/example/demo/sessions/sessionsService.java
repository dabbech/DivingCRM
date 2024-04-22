package com.example.demo.sessions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class sessionsService {

    private final sessionsRepository repository;

    public void save(sessionsRequest request) {
        var session = sessions.builder()
                .id(request.getId())
                .date(request.getDate())
                .createdBy(request.getCreatedBy())
                .build();
        repository.save(session);
    }

    public List<sessions> findAll() {
        return repository.findAll();
    }

    public Optional<sessions> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(UUID id, sessionsRequest request) {
        Optional<sessions> optionalSessions = repository.findById(id);
        if (optionalSessions.isPresent()) {
            sessions sessions = optionalSessions.get();
            sessions.setDate(request.getDate());
            sessions.setCreatedBy(request.getCreatedBy());
            repository.save(sessions);
        } else {
            // Handle case where session with given id is not found
            throw new IllegalArgumentException("Session not found with id: " + id);
        }
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
