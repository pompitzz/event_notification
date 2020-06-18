package me.sun.notification_service.crawler.weather.dto.response;

import lombok.Data;

@Data
public class ForecastResponse {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private String nx;
    private String ny;
}
