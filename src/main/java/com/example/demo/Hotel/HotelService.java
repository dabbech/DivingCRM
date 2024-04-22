package com.example.demo.Hotel;

import com.example.demo.Agents.Agents;
import com.example.demo.DiveType.DiveType;
import com.example.demo.DiveType.DiveTypeResponse;
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
public class HotelService {
    private final HotelRepository repository;
    private final UserRepository userRepository;
    public void save(HotelRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        var hotel = Hotel.builder()
                .id(request.getId())
                .name(request.getName())
                .club(currentUser.getClub())
                .build();
        repository.save(hotel);
    }
    public List<HotelResponse> getHotelsByClub() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        List<Hotel> Hotels =  repository.findByClub(currentUser.getClub());
        List<HotelResponse> HotelsResponse = new ArrayList<>();
        for (Hotel agent : Hotels) {
            HotelResponse response = HotelResponse.builder()
                    .name(agent.getName())
                    .build();
            HotelsResponse.add(response);
        }
        return HotelsResponse;
    }
    public List<Hotel> findAll() {
        return repository.findAll();
    }

    public Optional<Hotel> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(UUID id, HotelRequest request) {
        Optional<Hotel> optionalHotel = repository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setName(request.getName());
            repository.save(hotel);
        } else {
            // Handle case where hotel with given id is not found
            throw new IllegalArgumentException("Hotel not found with id: " + id);
        }
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
