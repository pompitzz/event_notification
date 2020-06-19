package me.sun.notification_service.crawler.forecast.dto.request;

import me.sun.notification_service.service.http.Parameterizable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class ForecastRequestParam implements Parameterizable {
    private String serviceKey;
    private Integer pageNo = 1;
    private Integer numOfRows = 30;
    private String dataType = "JSON";
    private String base_date;
    private String base_time;
    private String nx;
    private String ny;

    public static ForecastRequestParam build(String serviceKey, String time, String nx, String ny) {
        final ForecastRequestParam forecastRequestParam = new ForecastRequestParam();
        forecastRequestParam.serviceKey = serviceKey;
        forecastRequestParam.base_date = findDate(); //TODO change logic
        forecastRequestParam.base_time = time;
        forecastRequestParam.nx = nx;
        forecastRequestParam.ny = ny;
        return forecastRequestParam;
    }

    private static String findDate() {
        LocalDate date = LocalDate.now();
        return StringUtils.replace(date.toString(), "-", "");
    }
}
