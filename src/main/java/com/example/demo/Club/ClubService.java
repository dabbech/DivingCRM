package com.example.demo.Club;

import com.example.demo.Agents.Agents;
import com.example.demo.Agents.AgentsRepository;
import com.example.demo.Agents.AgentsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository repository;

    public void save(ClubRequest request) {
        var Clubs = Club.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        repository.save(Clubs);
    }


    public List<Club> findAll() {
        return repository.findAll();
    }
}
