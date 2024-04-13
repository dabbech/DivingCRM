package com.example.demo.Reservation;

import com.example.demo.sessions.sessions;
import com.example.demo.sessions.sessionsRequest;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Reservation")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ReservationController {
    private final ReservationService service;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")

    public ResponseEntity<?> save(
            @RequestBody ReservationRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")

    public ResponseEntity<List<Reservation>> findAllreservations() {
        return ResponseEntity.ok(service.findAll());
    }
}
