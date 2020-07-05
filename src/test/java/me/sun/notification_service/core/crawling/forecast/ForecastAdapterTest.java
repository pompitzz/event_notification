package me.sun.notification_service.core.crawling.forecast;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@Disabled
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

        System.out.println("test");

        // when
//        final List<ForecastResponse> result = forecastAdapter.request(time, new Location("", "62", "126"), targetSize);

        // then
//        assertThat(result.size() == targetSize).isTrue();
    }

}