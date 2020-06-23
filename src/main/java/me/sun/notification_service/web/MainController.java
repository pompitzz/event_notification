package me.sun.notification_service.web;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.crawler.forecast.ForecastAdapter;
import me.sun.notification_service.crawler.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.tasks.ForecastTaskFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ForecastTaskFacade forecastTaskFacade;

    @RequestMapping
    public void print() {
        forecastTaskFacade.run(LocalTime.of(8, 15), "62", "126");
    }
}
