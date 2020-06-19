package me.sun.notification_service.crawler.forecast.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum ForecastTime {
    TWO(LocalTime.of(2, 0)),
    FIVE(LocalTime.of(5, 0)),
    EIGHT(LocalTime.of(8, 0)),
    ELEVEN(LocalTime.of(11, 0)),
    FOURTEEN(LocalTime.of(14, 0)),
    SEVENTEEN(LocalTime.of(17, 0)),
    TWENTY(LocalTime.of(20, 0)),
    TWENTY_THREE(LocalTime.of(23, 0));

    private final LocalTime localTime;

    public String timeString() {
        return StringUtils.replace(localTime.toString(), ":", "");
    }

    public static ForecastTime findOptimalForecastTime(LocalTime time) {
        if (time.isBefore(firstCanRequestTime())) {
            throw new IllegalArgumentException("02:10 이전에는 조회가 불가능합니다.");
        }
        ForecastTime result = ForecastTime.TWO;
        for (ForecastTime apiTime : ForecastTime.values()) {
            if (apiTime.canRequest(time)) {
                result = apiTime;
            } else {
                break;
            }
        }
        return result;
    }

    private LocalTime canRequestTime() {
        return this.localTime.plusMinutes(10);
    }

    private boolean canRequest(LocalTime time) {
        LocalTime canRequestTime = this.canRequestTime();
        // 요청 시간보다 크거나, 같어야 요청이 가능하다.
        return canRequestTime.compareTo(time) <= 0;
    }

    private static LocalTime firstCanRequestTime() {
        return ForecastTime.TWO.localTime.plusMinutes(10);
    }
}
