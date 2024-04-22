package com.example.demo.Club;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository repository;

    public void save(ClubRequest request) {
        var club = Club.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        repository.save(club);
    }

    public List<Club> findAll() {
        return repository.findAll();
    }

    public Optional<Club> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(UUID id, ClubRequest request) {
        Optional<Club> optionalClub = repository.findById(id);
        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();
            club.setName(request.getName());
            repository.save(club);
        } else {
            // Handle case where club with given id is not found
            throw new IllegalArgumentException("Club not found with id: " + id);
        }
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
