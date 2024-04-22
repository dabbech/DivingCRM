package com.example.demo.Reservation;

import com.example.demo.Agents.Agents;
import com.example.demo.Agents.AgentsRepository;
import com.example.demo.DiveType.DiveType;
import com.example.demo.DiveType.DiveTypeRepository;
import com.example.demo.Hotel.Hotel;
import com.example.demo.Hotel.HotelRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository repository;
    private final AgentsRepository agentsRepository;
    private final HotelRepository hotelRepository;
    private final DiveTypeRepository diveTypeRepository;
    private final UserRepository userRepository;

    public List<ReservationResponse> getReservationsByClub(UUID clubId) {
        List<User> users = userRepository.findByClubId(clubId);
        List<Reservation> reservations = new ArrayList<>();
        for (User user : users) {
            reservations.addAll(user.getReservations());
        }
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationResponse response = ReservationResponse.builder()
                    .date(reservation.getDate())
                    .name(reservation.getName())
                    .numberOfPersons(reservation.getNumberOfPersons())
                    .needsPickUp(reservation.isNeedsPickUp())
                    .price(reservation.getPrice())
                    .build();
            reservationResponses.add(response);
        }
        return reservationResponses;
    }
    public List<ReservationResponse> getReservationsByAgent(UUID agentId) {
        List<Reservation> reservations = repository.findByAgentId(agentId);
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationResponse response = ReservationResponse.builder()
                    .date(reservation.getDate())
                    .name(reservation.getName())
                    .numberOfPersons(reservation.getNumberOfPersons())
                    .needsPickUp(reservation.isNeedsPickUp())
                    .price(reservation.getPrice())
                    .build();
            reservationResponses.add(response);
        }
        return reservationResponses;
    }


    public void update(UUID id, ReservationRequest request) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));

        updateReservationFromRequest(reservation, request);

        repository.save(reservation);
    }

    public void save(ReservationRequest request) {
        // Retrieve the current user's ID from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        UUID currentUserId = currentUser.getId();
        // Build the reservation and set the user ID
        Reservation reservation = buildReservationFromRequest(request, currentUserId);

        // Save the reservation
        repository.save(reservation);
    }

    // Other methods remain unchanged...

    private Reservation buildReservationFromRequest(ReservationRequest request, UUID currentUserId) {
        return Reservation.builder()
                .id(request.getId())
                .date(request.getDate())
                .name(request.getName())
                .numberOfPersons(request.getNumberOfPersons())
                .needsPickUp(request.isNeedsPickUp())
                .price(request.getPrice())
                .agent(request.getAgentId() != null ? fetchAgent(request.getAgentId()) : null)
                .hotel(request.getHotelId() != null ? fetchHotel(request.getHotelId()) : null)
                .typeOfDive(request.getDiveTypeId() != null ? fetchDiveType(request.getDiveTypeId()) : null)
                // Set the user ID
                .createdBy(userRepository.getById(currentUserId))
                .build();
    }

    private void updateReservationFromRequest(Reservation reservation, ReservationRequest request) {
        reservation.setDate(request.getDate());
        reservation.setName(request.getName());
        reservation.setNumberOfPersons(request.getNumberOfPersons());
        reservation.setNeedsPickUp(request.isNeedsPickUp());
        reservation.setPrice(request.getPrice());
        reservation.setAgent(request.getAgentId() != null ? fetchAgent(request.getAgentId()) : null);
        reservation.setHotel(request.getHotelId() != null ? fetchHotel(request.getHotelId()) : null);
        reservation.setTypeOfDive(request.getDiveTypeId() != null ? fetchDiveType(request.getDiveTypeId()) : null);
    }
    private Agents fetchAgent(UUID agentId) {
        return agentsRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found with id: " + agentId));
    }

    private Hotel fetchHotel(UUID hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found with id: " + hotelId));
    }

    private DiveType fetchDiveType(UUID diveTypeId) {
        return diveTypeRepository.findById(diveTypeId)
                .orElseThrow(() -> new IllegalArgumentException("DiveType not found with id: " + diveTypeId));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
