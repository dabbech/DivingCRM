package com.example.demo.Hotel;

import com.example.demo.DiveType.DiveType;
import com.example.demo.DiveType.DiveTypeRequest;
import com.example.demo.DiveType.DiveTypeService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Hotel")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class HotelController {
    private final HotelService service;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ResponseEntity<?> save(
            @RequestBody HotelRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    @Hidden
    public ResponseEntity<List<Hotel>> findAllreservations() {
        return ResponseEntity.ok(service.findAll());
    }
}
