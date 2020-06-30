package me.sun.notification_service.core.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalTime;


@Builder
@AllArgsConstructor
public class ForecastTask implements Runnable {

    private ForecastTaskFacade forecastTaskFacade;
    private LocalTime time;
    private String nx;
    private String ny;

    @Override
    public void run() {
        forecastTaskFacade.run(time, nx, ny);
    }
}
