package com.example.demo.Agents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgentsService {
    private final AgentsRepository repository;

    public void save(AgentsRequest request) {
        var agent = Agents.builder()
                .id(request.getId())
                .agentName(request.getAgentName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        repository.save(agent);
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
