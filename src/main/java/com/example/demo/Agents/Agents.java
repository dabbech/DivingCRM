package com.example.demo.Agents;

import com.example.demo.Club.Club;
import jakarta.annotation.Nullable;
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
    @Nullable
    private int percentage ;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}