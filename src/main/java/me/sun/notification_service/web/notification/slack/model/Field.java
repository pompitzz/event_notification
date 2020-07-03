package me.sun.notification_service.web.notification.model.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Field {
    private String title;
    private String value;
    @JsonProperty("short")
    private boolean isShort;
}
