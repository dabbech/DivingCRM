package com.example.demo.Reservation;

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
    private UUID createdBy;
    private String name;
    private int numberOfPersons;
    private String hotel;
    private boolean needsPickUp;
    private String typeOfDive;
    private double price;
    private UUID agentId;
}