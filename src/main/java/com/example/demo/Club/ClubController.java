package com.example.demo.Club;

import com.example.demo.Agents.Agents;
import com.example.demo.Agents.AgentsRequest;
import com.example.demo.Agents.AgentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
