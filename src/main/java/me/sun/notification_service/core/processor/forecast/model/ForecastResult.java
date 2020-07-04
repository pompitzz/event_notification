package me.sun.notification_service.core.processor.forecast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class ForecastResult {

    private String locationInformation;
    private LocalDate forecastDate;
    private List<TimeMeasureValue> timeMeasureValues;

    public String getTitle() {
        return locationInformation + " | " + forecastDate;
    }

    public List<MeasureValue> getRainMeasureValues() {
        return timeMeasureValues.stream()
                .map(TimeMeasureValue::getMeasureValues)
                .flatMap(Collection::stream)
                .filter(MeasureValue::isRainCategory)
                .collect(Collectors.toList());
    }

    public double highestRainPercent() {
        return getRainMeasureValues().stream()
                .map(MeasureValue::getMeasureValue)
                .mapToDouble(Double::parseDouble)
                .max()
                .orElse(0);

    }
}
