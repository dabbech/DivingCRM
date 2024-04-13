package com.example.demo.Reservation;

import com.example.demo.sessions.sessions;
import com.example.demo.Reservation.ReservationRepository;
import com.example.demo.sessions.sessionsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class ReservationService {
    private final ReservationRepository repository;

    public void save(ReservationRequest request) {
        var Reservations = Reservation.builder()
                .id(request.getId())
                .date(request.getDate())
                .createdBy(request.getCreatedBy())
                .agentId(request.getAgentId())
                .name(request.getName())
                .numberOfPersons(request.getNumberOfPersons())
                .hotel(request.getHotel())
                .needsPickUp(request.isNeedsPickUp())
                .typeOfDive(request.getTypeOfDive())
                .price(request.getPrice())
                .build();
        repository.save(Reservations);
    }


    public List<Reservation> findAll() {
        return repository.findAll();
    }

}
