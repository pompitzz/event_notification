package me.sun.notification_service.core.crawling.forecast.dto.request;

import me.sun.notification_service.infrastructure.Parameterizable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class ForecastRequestParam implements Parameterizable {
    private String serviceKey;
    private Integer pageNo = 1;
    private Integer numOfRows = 100;
    private String dataType = "JSON";
    private String base_date;
    private String base_time;
    private String nx;
    private String ny;

    public static ForecastRequestParam build(String serviceKey, String time, String nx, String ny, int size) {
        final ForecastRequestParam forecastRequestParam = new ForecastRequestParam();
        forecastRequestParam.serviceKey = serviceKey;
        forecastRequestParam.base_date = findDate(); //TODO change logic
        forecastRequestParam.base_time = time;
        forecastRequestParam.nx = nx;
        forecastRequestParam.ny = ny;
        forecastRequestParam.numOfRows = size;
        return forecastRequestParam;
    }

    private static String findDate() {
        LocalDate date = LocalDate.now();
        return StringUtils.replace(date.toString(), "-", "");
    }
}