package com.example.demo.Hotel;

import com.example.demo.DiveType.DiveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
}
