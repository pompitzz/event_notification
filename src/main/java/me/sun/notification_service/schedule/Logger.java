package me.sun.notification_service.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Logger {
    public void print(String message) {
        log.warn(message);
    }
}
