package com.example.notification.controller;

import com.example.notification.metrics.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {

        return Map.of(
                "openIncidents", metricsService.getOpenIncidents(),
                "criticalIncidents", metricsService.getCriticalIncidents(),
                "avgAcknowledgementTimeSeconds",
                metricsService.getAverageAcknowledgementTime()
        );
    }
}