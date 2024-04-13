package com.example.demo.Agents;

import com.example.demo.Reservation.Reservation;
import com.example.demo.Reservation.ReservationRequest;
import com.example.demo.Reservation.ReservationService;
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
public class AgentsController {
    private final AgentsService service;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ResponseEntity<?> save(
            @RequestBody AgentsRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    @Hidden
    public ResponseEntity<List<Agents>> findAllreservations() {
        return ResponseEntity.ok(service.findAll());
    }
}
