package com.example.notification.service;

import com.example.notification.dto.IncidentRequest;
import lombok.RequiredArgsConstructor;
import com.example.notification.model.Incident;
import com.example.notification.model.IncidentStatus;
import org.springframework.stereotype.Service;
import com.example.notification.repository.IncidentRepository;
import org.springframework.scheduling.annotation.Async;
import com.example.notification.orchestration.EscalationOrchestrator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class IncidentService {
    private final IncidentRepository repository;
    private final EscalationOrchestrator orchestrator;

    public Incident createIncident(IncidentRequest request) {

        Incident incident = Incident.builder()
                .triggeredBy(request.getTriggeredBy())
                .severity(request.getSeverity())
                .status(IncidentStatus.OPEN)
                .escalationLevel(0)
                .createdAt(LocalDateTime.now())
                .build();

        Incident saved = repository.save(incident);

        orchestrator.startEscalation(saved.getId());

        return saved;
    }

    public Incident acknowledge(Long id) {
        Incident incident = repository.findById(id).orElseThrow();
        incident.setStatus(IncidentStatus.ACKNOWLEDGED);
        incident.setAcknowledgedAt(LocalDateTime.now());
        return repository.save(incident);
    }

    public List<Incident> getAll() {
        return repository.findAll();
    }

}
