package me.sun.notification_service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TempTask implements Runnable {
    private final Logger logger;
    @Override
    public void run() {
        logger.print("Temp Task GOGO");
    }
}
