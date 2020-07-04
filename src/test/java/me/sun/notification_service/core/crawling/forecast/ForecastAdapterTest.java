package me.sun.notification_service.core.crawling.forecast;

import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ForecastAdapterTest {

    @Autowired
    private ForecastAdapter forecastAdapter;

    @Test
    void request() throws Exception {
        // given
        final LocalTime time = LocalTime.now();
        final int targetSize = 10;

        if (time.compareTo(LocalTime.of(2, 10)) < 0) {
            return;
        }

        // when
//        final List<ForecastResponse> result = forecastAdapter.request(time, new Location("", "62", "126"), targetSize);

        // then
//        assertThat(result.size() == targetSize).isTrue();
    }

}