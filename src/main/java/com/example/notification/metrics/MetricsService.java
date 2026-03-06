package com.example.notification.metrics;

import com.example.notification.model.Incident;
import com.example.notification.model.IncidentStatus;
import com.example.notification.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final IncidentRepository repository;

    public long getOpenIncidents() {
        return repository.findAll()
                .stream()
                .filter(i -> i.getStatus() != IncidentStatus.RESOLVED)
                .count();
    }

    public long getCriticalIncidents() {
        return repository.findAll()
                .stream()
                .filter(i -> i.getSeverity().name().equals("CRITICAL"))
                .count();
    }

    public long getAverageAcknowledgementTime() {

        List<Incident> incidents = repository.findAll();

        return (long) incidents.stream()
                .filter(i -> i.getAcknowledgedAt() != null)
                .mapToLong(i ->
                        Duration.between(
                                i.getCreatedAt(),
                                i.getAcknowledgedAt()
                        ).getSeconds())
                .average()
                .orElse(0);
    }
}