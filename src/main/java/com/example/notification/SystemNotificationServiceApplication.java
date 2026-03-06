package com.example.notification;

import com.example.notification.config.EscalationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(EscalationProperties.class)
public class SystemNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemNotificationServiceApplication.class, args);
	}

}
