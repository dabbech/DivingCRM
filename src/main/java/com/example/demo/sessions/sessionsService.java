package com.example.demo.sessions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class sessionsService {

    private final sessionsRepository repository;

    public void save(sessionsRequest request) {
        var Sessions = sessions.builder()
                .id(request.getId())
                .date(request.getDate())
                .createdBy(request.getCreatedBy())
                .build();
        repository.save(Sessions);
    }

    public List<sessions> findAll() {
        return repository.findAll();
    }
}
