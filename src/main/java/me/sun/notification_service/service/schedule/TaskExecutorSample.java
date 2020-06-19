package me.sun.notification_service.service.schedule;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.schedule.Logger;
import me.sun.notification_service.schedule.TempTask;
import me.sun.notification_service.utils.DateUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskExecutorSample {
    @AllArgsConstructor
    private static class MessagePrintTask implements Runnable{

        private String message;

        @Override
        public void run() {
            log.info(message);
        }
    }

    private final TaskScheduler taskScheduler;
    private final Logger logger;
    private final TempTask tempTask;
    public void printMessage() {
        log.info("Start Scheduled After 3 sec. And task repeatably 1sec");
        final LocalDateTime of = LocalDateTime.now().plusSeconds(10);
        log.info("after 10 sec Scheduled {}", of);
        final ScheduledFuture<?> future = taskScheduler.schedule(tempTask, DateUtils.parse(of));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tempTask.run();
    }

    public static void main(String[] args) {
        final LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        final Date from = Date.from(now.toInstant(ZoneOffset.ofHours(9)));
        System.out.println(from);
        System.out.println(new Date(System.currentTimeMillis() + 3000));
    }
}
