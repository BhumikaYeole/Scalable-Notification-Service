package com.example.notification.controller;

import com.example.notification.dto.IncidentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.notification.model.Incident;
import org.springframework.web.bind.annotation.*;
import com.example.notification.service.IncidentService;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor

public class IncidentController {
    private final IncidentService service;

    @PostMapping
    public Incident create(@Valid @RequestBody IncidentRequest request){
        return service.createIncident(request);
    }

    @GetMapping
    public List<Incident> getAll() {
        return service.getAll();
    }

    @PostMapping("/{id}/acknowledge")
    public Incident acknowledge(@PathVariable Long id) {
        return service.acknowledge(id);
    }

}
