package me.sun.notification_service.service.message.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.notification.slack.dto.Field;
import me.sun.notification_service.utils.MessageUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
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
