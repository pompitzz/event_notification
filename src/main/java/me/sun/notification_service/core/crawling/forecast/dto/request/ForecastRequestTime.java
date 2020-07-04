package me.sun.notification_service.core.crawling.forecast.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum ForecastRequestTime {
    TWO(LocalTime.of(2, 0)),
    FIVE(LocalTime.of(5, 0)),
    EIGHT(LocalTime.of(8, 0)),
    ELEVEN(LocalTime.of(11, 0)),
    FOURTEEN(LocalTime.of(14, 0)),
    SEVENTEEN(LocalTime.of(17, 0)),
    TWENTY(LocalTime.of(20, 0)),
    TWENTY_THREE(LocalTime.of(23, 0));

    private final LocalTime localTime;

    public static LocalDateTime findOptimalForecastLocalDateTime(LocalDate date, LocalTime time) {
        if (canNotTodayRequest(time)) {
            final LocalDate minusDays = date.minusDays(1);
            final LocalTime optimalTime = findOptimalTime(LocalTime.MIDNIGHT);
            return LocalDateTime.of(minusDays, optimalTime);
        }

        final LocalTime optimalTime = findOptimalTime(time);
        return LocalDateTime.of(date, optimalTime);
    }

    private static boolean canNotTodayRequest(LocalTime time) {
        return time.isBefore(firstCanRequestTime());
    }

    private static LocalTime firstCanRequestTime() {
        return ForecastRequestTime.TWO.localTime.plusMinutes(10);
    }

    private static LocalTime findOptimalTime(LocalTime time) {
        ForecastRequestTime result = ForecastRequestTime.TWO;
        for (ForecastRequestTime apiTime : ForecastRequestTime.values()) {
            if (apiTime.canRequest(time)) {
                result = apiTime;
            } else {
                break;
            }
        }
        return result.localTime;
    }

    private LocalTime canRequestTime() {
        return this.localTime.plusMinutes(10);
    }

    private boolean canRequest(LocalTime time) {
        LocalTime canRequestTime = this.canRequestTime();
        // 요청 시간보다 크거나, 같어야 요청이 가능하다.
        return canRequestTime.compareTo(time) <= 0;
    }
}
