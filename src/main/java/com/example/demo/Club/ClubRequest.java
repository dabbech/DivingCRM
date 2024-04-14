package com.example.demo.Club;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ClubRequest {
    private UUID id;

    private String name;

}
