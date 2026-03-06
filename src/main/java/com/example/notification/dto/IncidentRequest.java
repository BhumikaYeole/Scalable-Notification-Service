package com.example.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.notification.model.IncidentSeverity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IncidentRequest {

    @NotBlank
    private String triggeredBy;

    @NotNull
    private IncidentSeverity severity;
}