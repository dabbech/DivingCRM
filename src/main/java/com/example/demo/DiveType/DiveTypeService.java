package com.example.demo.DiveType;

import com.example.demo.Agents.Agents;
import com.example.demo.Agents.AgentsResponse;
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
public class DiveTypeService {
    private final DiveTypeRepository repository;
    private final UserRepository userRepository;

    public void save(DiveTypeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        var diveType = DiveType.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .club(currentUser.getClub())
                .build();
        repository.save(diveType);
    }
    public List<DiveTypeResponse> getdiveTypeByClub() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        List<DiveType> diveTypes = repository.findByClub(currentUser.getClub());
        List<DiveTypeResponse> diveTypeResponse = new ArrayList<>();
        for (DiveType agent : diveTypes) {
            DiveTypeResponse response = DiveTypeResponse.builder()
                    .price(agent.getPrice())
                    .name(agent.getName())

                    .build();
            diveTypeResponse.add(response);
        }
        return diveTypeResponse;
    }
    public List<DiveType> findAll() {
        return repository.findAll();
    }

    public Optional<DiveType> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(UUID id, DiveTypeRequest request) {
        Optional<DiveType> optionalDiveType = repository.findById(id);
        if (optionalDiveType.isPresent()) {
            DiveType diveType = optionalDiveType.get();
            diveType.setName(request.getName());
            diveType.setPrice(request.getPrice());
            repository.save(diveType);
        } else {
            // Handle case where dive type with given id is not found
            throw new IllegalArgumentException("Dive type not found with id: " + id);
        }
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
