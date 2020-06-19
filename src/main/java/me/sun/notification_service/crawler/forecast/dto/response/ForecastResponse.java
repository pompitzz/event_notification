package me.sun.notification_service.crawler.forecast.dto.response;

import lombok.Getter;
import lombok.ToString;
import me.sun.notification_service.crawler.forecast.model.ForecastCategory;
import me.sun.notification_service.domain.forecast.Forecast;
import me.sun.notification_service.domain.town.Town;

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
        return Forecast.builder()
                .forecastCategory(ForecastCategory.valueOf(category))
                .forecastDateTime(toLocalDateTime(fcstDate, fcstTime))
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
}
