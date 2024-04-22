package com.example.demo.DiveType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DiveTypeResponse {

    private String name;
    private double price;
}
