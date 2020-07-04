package me.sun.notification_service.core.processor.forecast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TimeMeasureValue {
    private LocalTime time;
    private List<MeasureValue> measureValues;
}