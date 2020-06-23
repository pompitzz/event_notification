package me.sun.notification_service.crawler.forecast.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ForecastProperty {
    private String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
    private String serviceKey = "Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D";
}
