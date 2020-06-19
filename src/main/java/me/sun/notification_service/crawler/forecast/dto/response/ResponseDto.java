package me.sun.notification_service.crawler.forecast.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.sun.notification_service.crawler.forecast.model.ForecastCategory;
import me.sun.notification_service.utils.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseDto {
    private Response response;

    public List<ForecastResponse> getForecast() {
        List<ForecastResponse> forecastResponses = this.response.getBody().getItems().getForecastResponses();
        return StreamUtils.stream(forecastResponses)
                .filter(response -> ForecastCategory.isContains(response.getCategory()))
                .collect(Collectors.toList());
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


