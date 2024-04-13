package com.example.demo.DiveType;

import com.example.demo.Reservation.Reservation;
import com.example.demo.Reservation.ReservationRepository;
import com.example.demo.Reservation.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DiveTypeService {
    private final DiveTypeRepository repository;

    public void save(DiveTypeRequest request) {
        var DiveTypes = DiveType.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build();
        repository.save(DiveTypes);
    }


    public List<DiveType> findAll() {
        return repository.findAll();
    }

}
