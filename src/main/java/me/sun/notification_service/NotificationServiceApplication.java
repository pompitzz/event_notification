package me.sun.notification_service;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.crawler.forecast.ForecastAdapter;
import me.sun.notification_service.crawler.forecast.dto.request.Location;
import me.sun.notification_service.crawler.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.service.schedule.TaskExecutorSample;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalTime;
import java.util.List;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
