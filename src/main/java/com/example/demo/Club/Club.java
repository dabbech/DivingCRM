package com.example.demo.Club;



import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id") // Reference to the admin responsible for this club
    private User admin;

    // Add other club-specific fields as needed

    @OneToMany(mappedBy = "club")
    private List<User> managers;
}
