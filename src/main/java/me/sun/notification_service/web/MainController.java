package me.sun.notification_service.web;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.scheduler.tasks.ForecastProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ForecastProcessor forecastProcessor;

    @RequestMapping
    public void print() {
    }
}
