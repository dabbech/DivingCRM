package com.example.demo.Agents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AgentsResponse {
    private String agentName;
    private String phoneNumber;
    private int percentage;
}
