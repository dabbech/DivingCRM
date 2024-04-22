package com.example.demo.Hotel;

import com.example.demo.DiveType.DiveType;
import com.example.demo.DiveType.DiveTypeRequest;
import com.example.demo.DiveType.DiveTypeService;
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
@RequestMapping("/api/v1/Hotel")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class HotelController {
    private final HotelService service;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")

    public ResponseEntity<?> save(
            @RequestBody HotelRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")

    public ResponseEntity<List<HotelResponse>> findHotelsByClub() {
        return ResponseEntity.ok(service.getHotelsByClub());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody HotelRequest request
    ) {
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
