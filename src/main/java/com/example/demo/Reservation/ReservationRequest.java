package com.example.demo.Reservation;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
public class ReservationRequest {

    private UUID id;
    private LocalDateTime date;
    private String name;
    private int numberOfPersons;
    private boolean needsPickUp;
    private double price;
    @Nullable
    private UUID agentId;
    @Nullable
    private UUID hotelId;
    @Nullable
    private UUID diveTypeId;
}