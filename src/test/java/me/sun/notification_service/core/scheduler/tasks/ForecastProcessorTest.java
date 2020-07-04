package me.sun.notification_service.core.scheduler.tasks;

import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocationRepository;
import me.sun.notification_service.core.processor.forecast.ForecastProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForecastProcessorTest {

    @Autowired
    private ForecastProcessor forecastProcessor;

    @Autowired
    private ForecastLocationRepository forecastLocationRepository;

    @Test
    void request()throws Exception {

        String[] tokens = {"서울특별시", "강동구", "천호제3동", "62", "126"};
        final ForecastLocation forecastLocation = ForecastLocation.builder()
                .state(tokens[0])
                .city(tokens[1])
                .addressDetail(tokens[2])
                .locationX(tokens[3])
                .locationY(tokens[4])
                .build();

        forecastLocationRepository.save(forecastLocation);

//        forecastProcessor.process(LocalTime.of(8, 30), Location.build(town));
    }

}