package me.sun.notification_service.core.crawling.forecast.dto.response;

import lombok.Getter;
import lombok.ToString;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.forecast.Forecast;
import me.sun.notification_service.core.domain.forecast.town.Town;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class ForecastResponse {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private String nx;
    private String ny;

    public Forecast toEntity(Town town) {
        final LocalDateTime localDateTime = toLocalDateTime(fcstDate, fcstTime);
        return Forecast.builder()
                .forecastCategory(ForecastCategory.valueOf(category))
                .forecastDate(localDateTime.toLocalDate())
                .forecastTime(localDateTime.toLocalTime())
                .measureValue(fcstValue)
                .town(town)
                .build();
    }

    private LocalDateTime toLocalDateTime(String date, String time) {
        //202006181111
        String dateTime = date + time;
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    public static void main(String[] args) {
        LocalDateTime parse = LocalDateTime.parse("202006161111", DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        System.out.println(parse);
    }

//    @Override
//    public String toString() {
//        final LocalDateTime baseLocalDateTime = toLocalDateTime(baseDate, baseTime);
//        final LocalDateTime fcstLocalDateTime = toLocalDateTime(fcstDate, fcstTime);
//        final ForecastCategory forecastCategory = ForecastCategory.valueOf(category);
//        return String.format(
//                "발표 시각: %s\n" +
//                        "측정 시각: %s\n" +
//                        "카테고리: %s\n" +
//                        "측정값: %s%s",
//                baseLocalDateTime, fcstLocalDateTime, forecastCategory.getDescription(), this.fcstValue, forecastCategory.getUnit());
//
//    }
}
