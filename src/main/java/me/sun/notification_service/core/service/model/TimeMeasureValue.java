package me.sun.notification_service.core.service.model;

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

    public String toMessage() {
        final String measureValueMessage = MessageUtils.joining(" ", measureValues, MeasureValue::toMessage);
        return String.format("%s -> %s", time, measureValueMessage);
    }

    public Field toField() {
        final String title = "시간: " + time;
        final List<MeasureValue> isNotRainValues = measureValues.stream()
                .filter(MeasureValue::isNotRainCategory)
                .collect(Collectors.toList());

        final String value = MessageUtils.joining("\n", isNotRainValues, MeasureValue::toMessage);
        return Field.builder()
                .title(title)
                .value(value)
                .build();
    }
}
