package me.sun.notification_service.web;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.processor.forecast.ForecastProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ForecastProcessor forecastProcessor;

    @RequestMapping
    public void print() {
    }
}
