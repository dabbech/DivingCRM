package com.example.demo.Reservation;

import com.example.demo.Agents.Agents;
import com.example.demo.DiveType.DiveType;
import com.example.demo.Hotel.Hotel;
import com.example.demo.sessions.sessions;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime date;



    private String name;
    private int numberOfPersons;
    private boolean needsPickUp;
    @ManyToOne(optional = true)
    @JoinColumn(name = "divetype_id")
    private DiveType typeOfDive;
    private double price;

    @ManyToOne(optional = true)
    @JoinColumn(name = "agent_id")
    private Agents agent;

    @ManyToOne(optional = true)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;


}
