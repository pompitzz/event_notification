package me.sun.notification_service.core.scheduler.tasks;

import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.domain.forecast.town.Town;
import me.sun.notification_service.core.domain.forecast.town.TownRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
class ForecastProcessorTest {

    @Autowired
    private ForecastProcessor forecastProcessor;

    @Autowired
    private TownRepository townRepository;

    @Test
    void request()throws Exception {

        String[] tokens = {"서울특별시", "강동구", "천호제3동", "62", "126"};
        final Town town = Town.builder()
                .state(tokens[0])
                .city(tokens[1])
                .addressDetail(tokens[2])
                .locationX(tokens[3])
                .locationY(tokens[4])
                .build();

        townRepository.save(town);

        forecastProcessor.process(LocalTime.of(8, 30), Location.build(town));
    }

}