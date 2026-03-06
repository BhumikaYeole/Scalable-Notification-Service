package com.example.notification.orchestration;

import com.example.notification.config.EscalationProperties;
import com.example.notification.model.Incident;
import com.example.notification.model.IncidentStatus;
import com.example.notification.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class EscalationOrchestrator {
    private final IncidentRepository repository;
    private final EscalationProperties escalationProperties;

    @Async
    public void startEscalation(Long id) {

        Optional<Incident> optional = repository.findById(id);
        if (optional.isEmpty()) return;

        Incident incident = optional.get();

        while (incident.getEscalationLevel() < 3
                && incident.getStatus() != IncidentStatus.ACKNOWLEDGED) {

            try {
                int delay = escalationProperties
                        .getDelay()
                        .get(incident.getSeverity().name());

                Thread.sleep(delay * 1000L);

                incident.setEscalationLevel(incident.getEscalationLevel() + 1);

                if (incident.getEscalationLevel() == 1)
                    incident.setStatus(IncidentStatus.NOTIFIED);

                else
                    incident.setStatus(IncidentStatus.ESCALATED);

                repository.save(incident);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}
