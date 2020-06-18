package me.sun.notification_service.crawler.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sun.notification_service.crawler.weather.dto.request.ForecastRequestParam;
import me.sun.notification_service.crawler.weather.dto.request.ForecastTime;
import me.sun.notification_service.crawler.weather.dto.request.Location;
import me.sun.notification_service.crawler.weather.dto.response.ForecastResponse;
import me.sun.notification_service.crawler.weather.dto.response.ResponseDto;
import me.sun.notification_service.service.http.ParameterBuilder;
import me.sun.notification_service.utils.UrlUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ForecastService {

    private static ParameterBuilder parameterBuilder = new ParameterBuilder();
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws JsonProcessingException {
        String key = "Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D";
        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
        ForecastRequestParam param = new ForecastRequestParam(key, LocalDate.now().minusDays(1), ForecastTime.findOptimalForecastTime(LocalTime.of(3, 0)), Location.A);
        String url = UrlUtils.buildUrl(baseUrl, parameterBuilder.buildParameter(param));
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        DefaultUriBuilderFactory handler = new DefaultUriBuilderFactory();
        handler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        restTemplate.setUriTemplateHandler(handler);
        String forObject = restTemplate.getForObject(url, String.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ResponseDto map = objectMapper.readValue(forObject, ResponseDto.class);
        System.out.println(map.getForecast());
//        List<ForecastResponse> forecast = map.getForecast();
//        System.out.println("fr = " + forecast);
    }
}
// http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20200618&base_time=0500&nx=1&ny=1
// http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20200618&base_time=0500&nx=1&ny=1