package me.sun.notification_service.crawler.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseDto {
        private Response response;
//    List<ForecastResponse> item;
    public List<ForecastResponse> getForecast() {
        return this.response.getBody().getItems().getForecastResponses();
    }


    @Getter
    static class Response {
        BodyDto body;
    }

    @Getter
    static class BodyDto {
        ItemDto items;
    }

    @Getter
    static class ItemDto {
        @JsonProperty("item")
        List<ForecastResponse> forecastResponses;
    }
}


