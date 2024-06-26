package com.example.demo.sessions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class sessionsRequest {

    private UUID id;
    private LocalDateTime date;
    private UUID createdBy;
}
