package com.example.demo.Agents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AgentsRequest {
    private UUID id;

    private String agentName;
    private String phoneNumber;
}
