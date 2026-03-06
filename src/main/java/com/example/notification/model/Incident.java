package com.example.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String triggeredBy;

    @Enumerated(EnumType.STRING)
    private IncidentSeverity severity;

    @Enumerated(EnumType.STRING)
    private IncidentStatus status;

    private int escalationLevel;

    private LocalDateTime createdAt;
    private LocalDateTime acknowledgedAt;
}