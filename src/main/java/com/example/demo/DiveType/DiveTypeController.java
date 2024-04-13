package com.example.demo.DiveType;

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

    public ResponseEntity<List<DiveType>> findAllreservations() {
        return ResponseEntity.ok(service.findAll());
    }
}
