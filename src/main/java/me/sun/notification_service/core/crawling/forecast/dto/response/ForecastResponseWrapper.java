package me.sun.notification_service.core.crawling.forecast.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.sun.notification_service.infrastructure.utils.StreamUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponseWrapper {
    private Response response;

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


