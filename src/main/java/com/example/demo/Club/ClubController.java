package com.example.demo.Club;

import com.example.demo.Agents.Agents;
import com.example.demo.Agents.AgentsRequest;
import com.example.demo.Agents.AgentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody ClubRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Club>> findAllreservations() {
        return ResponseEntity.ok(service.findAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody ClubRequest request
    ) {
        try {
            service.update(id, request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
