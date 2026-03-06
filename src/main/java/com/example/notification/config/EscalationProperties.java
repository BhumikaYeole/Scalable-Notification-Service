package com.example.notification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "guardian.escalation")
@Getter
@Setter
public class EscalationProperties {

    private int maxLevel;
    private Map<String, Integer> delay;
}