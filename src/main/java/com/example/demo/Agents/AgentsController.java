package com.example.demo.Agents;

import com.example.demo.Reservation.Reservation;
import com.example.demo.Reservation.ReservationRequest;
import com.example.demo.Reservation.ReservationResponse;
import com.example.demo.Reservation.ReservationService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Agents")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AgentsController {
    private final AgentsService service;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")

    public ResponseEntity<List<AgentsResponse>> findAgentsByClub() {

        List<AgentsResponse> reservations = service.getAgentsByClub();
        return ResponseEntity.ok(reservations);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> save(@RequestBody AgentsRequest request) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

   /* @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<Agents>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }*/

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AgentsRequest request) {
        try {
            service.update(id, request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}