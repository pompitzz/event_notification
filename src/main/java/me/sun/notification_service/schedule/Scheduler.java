package me.sun.notification_service.schedule;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.utils.DateUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Scheduler {

    private final TaskScheduler taskScheduler;

    public void scheduling(Runnable task, LocalDateTime localDateTime) {
        taskScheduler.schedule(task, DateUtils.parse(localDateTime));
    }
}
