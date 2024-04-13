package com.example.demo.Agents;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Agents {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String agentName;
    private String phoneNumber;
}