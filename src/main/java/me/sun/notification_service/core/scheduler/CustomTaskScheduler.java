package me.sun.notification_service.core.scheduler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class CustomTaskScheduler extends ThreadPoolTaskScheduler {
    private final Map<Object, ScheduledFuture<?>> scheduledTaskFuture = new IdentityHashMap<>();

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        final ScheduledFuture<?> future = super.scheduleWithFixedDelay(task, startTime, delay);
        ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
        scheduledTaskFuture.put(runnable.getTarget(), future);
        return future;
    }

    public ScheduledFuture<?> get(Object object) {
        return scheduledTaskFuture.get(object);
    }
}
