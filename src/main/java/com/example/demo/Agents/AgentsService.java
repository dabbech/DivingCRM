package com.example.demo.Agents;

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
public class AgentsService {
    private final AgentsRepository repository;
    private final UserRepository userRepository;
    public void save(AgentsRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        var agent = Agents.builder()
                .id(request.getId())
                .agentName(request.getAgentName())
                .phoneNumber(request.getPhoneNumber())
                .percentage(request.getPercentage())
                .club(currentUser.getClub())
                .build();
        repository.save(agent);
    }

    public List<AgentsResponse> getAgentsByClub() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Assuming the user ID is stored as a string
        User currentUser = userRepository.findByEmail(username);
        List<Agents> uniqueAgents = repository.findByClub(currentUser.getClub());




        List<AgentsResponse> agentResponses = new ArrayList<>();
        for (Agents agent : uniqueAgents) {
            AgentsResponse response = AgentsResponse.builder()
                    .agentName(agent.getAgentName())
                    .phoneNumber(agent.getPhoneNumber())
                    .percentage(agent.getPercentage())
                    .build();
            agentResponses.add(response);
        }
        return agentResponses;
    }

    public List<Agents> findAll() {
        return repository.findAll();
    }

    public Optional<Agents> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(UUID id, AgentsRequest request) {
        Optional<Agents> optionalAgent = repository.findById(id);
        if (optionalAgent.isPresent()) {
            Agents agent = optionalAgent.get();
            agent.setAgentName(request.getAgentName());
            agent.setPhoneNumber(request.getPhoneNumber());
            repository.save(agent);
        } else {
            // Handle case where agent with given id is not found
            throw new IllegalArgumentException("Agent not found with id: " + id);
        }
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
