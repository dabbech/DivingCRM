package com.example.demo.Hotel;

import com.example.demo.Club.Club;
import com.example.demo.DiveType.DiveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    List<Hotel> findByClub(Club club);
}
