package com.example.demo.Reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReservationResponse {

    private LocalDateTime date;
    private String name;
    private int numberOfPersons;
    private boolean needsPickUp;
    private double price;
}
