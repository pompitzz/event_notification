package me.sun.notification_service.crawler.weather.dto.request;

import me.sun.notification_service.Parameterizable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class ForecastRequestParam implements Parameterizable {
    private String serviceKey;
    private Integer pageNo = 1;
    private Integer numOfRows = 30;
    private String dataType = "JSON";
    private String base_date;
    private String base_time;
    private Integer nx;
    private Integer ny;

    public ForecastRequestParam(String serviceKey, LocalDate date, ForecastTime forecastTime, Location location) {
        this.serviceKey = serviceKey;
        this.base_date = StringUtils.replace(date.toString(), "-", "");
        this.base_time = forecastTime.timeString();
        this.nx = location.getNx();
        this.ny = location.getNy();
    }

    public static void main(String[] args) {
        String a = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20200618&base_time=0500&nx=1&ny=1";
        String b = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20200618&base_time=0500&nx=1&ny=1";
        System.out.println(a.equals(b));
    }
}
