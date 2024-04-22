package com.example.demo.Reservation;

import com.example.demo.sessions.sessions;
import com.example.demo.sessions.sessionsRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Reservation")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ReservationController {
    private final ReservationService service;
    @Autowired
    private UserRepository userRepository;

    private UUID getCurrentUserClubId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Retrieve your domain User entity
        User currentUser = userRepository.findByEmail(username);

        // Convert your domain User to Spring Security's User

        return currentUser.getClub().getId();
    }


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

    public ResponseEntity<List<ReservationResponse>> findReservationsByClub() {
        UUID currentUserClubId = getCurrentUserClubId();
        List<ReservationResponse> reservations = service.getReservationsByClub(currentUserClubId);
        return ResponseEntity.ok(reservations);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateReservation(
            @PathVariable UUID id,
            @RequestBody ReservationRequest request) {
        try {
            service.update(id, request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteReservation(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
