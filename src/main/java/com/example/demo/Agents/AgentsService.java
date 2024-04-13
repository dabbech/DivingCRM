package com.example.demo.Agents;

import com.example.demo.Reservation.Reservation;
import com.example.demo.Reservation.ReservationRepository;
import com.example.demo.Reservation.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentsService {
    private final AgentsRepository repository;

    public void save(AgentsRequest request) {
        var Agent = Agents.builder()
                .id(request.getId())
                .agentName(request.getAgentName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        repository.save(Agent);
    }


    public List<Agents> findAll() {
        return repository.findAll();
    }

}
