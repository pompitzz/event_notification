package me.sun.notification_service.core.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.core.processor.forecast.ForecastProcessor;

import java.time.LocalTime;


@Builder
@AllArgsConstructor
public class ForecastTask implements Runnable {

    private ForecastProcessor forecastProcessor;
    private LocalTime time;
    private String nx;
    private String ny;

    @Override
    public void run() {
//        forecastProcessor.process(time, nx, ny);
    }
}
