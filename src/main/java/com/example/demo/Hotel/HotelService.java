package com.example.demo.Hotel;

import com.example.demo.DiveType.DiveType;
import com.example.demo.DiveType.DiveTypeRepository;
import com.example.demo.DiveType.DiveTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository repository;

    public void save(HotelRequest request) {
        var Hotels = Hotel.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        repository.save(Hotels);
    }


    public List<Hotel> findAll() {
        return repository.findAll();
    }
}
