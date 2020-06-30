package me.sun.notification_service.core.scheduler.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ScheduledSample {
//    @Async
//    @Scheduled(fixedDelay = 5000)
    public void doSomeThing() throws InterruptedException {
        log.info("This is scheduled 5 sec and sleep 3 sec");
        TimeUnit.SECONDS.sleep(3);
    }
}
