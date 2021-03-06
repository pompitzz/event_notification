package me.sun.notification_service.core.scheduler.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ScheduledSample2 {
    public void doSomeThing() throws InterruptedException {
        log.info("This is scheduled 3 sec and sleep 3 sec");
        TimeUnit.SECONDS.sleep(3);
    }
}
