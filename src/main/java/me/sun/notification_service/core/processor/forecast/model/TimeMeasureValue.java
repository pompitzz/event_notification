package me.sun.notification_service.core.service.builder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.web.notification.model.slack.dto.Field;
import me.sun.notification_service.infrastructure.utils.MessageUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class TimeMeasureValue {
    private LocalTime time;
    private List<MeasureValue> measureValues;
}