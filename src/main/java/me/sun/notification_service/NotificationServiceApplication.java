package me.sun.notification_service;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.service.schedule.TaskExecutorSample;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class NotificationServiceApplication {

    private final TaskExecutorSample taskExecutorSample;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);

    }

    @Bean
    public ApplicationRunner runner() {
        return args -> taskExecutorSample.printMessage();
    }

}
