package me.sun.notification_service.core.crawling.forecast.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ForecastProperty {
    private String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";

    @Value("${forecast.serviceKey}")
    private String serviceKey;
}
