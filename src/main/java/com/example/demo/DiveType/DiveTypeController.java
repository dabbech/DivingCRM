package com.example.demo.DiveType;

import com.example.demo.Reservation.Reservation;
import com.example.demo.Reservation.ReservationRequest;
import com.example.demo.Reservation.ReservationService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/DiveType")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DiveTypeController {
    private final DiveTypeService service;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")

    public ResponseEntity<?> save(
            @RequestBody DiveTypeRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")

    public ResponseEntity<List<DiveTypeResponse>> finddiveTypeByClub() {
        return ResponseEntity.ok(service.getdiveTypeByClub());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody DiveTypeRequest request) {
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
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
