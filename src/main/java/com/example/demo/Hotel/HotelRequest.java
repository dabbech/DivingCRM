package com.example.demo.Hotel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class HotelRequest {
    private UUID id;
    private String name;
}
