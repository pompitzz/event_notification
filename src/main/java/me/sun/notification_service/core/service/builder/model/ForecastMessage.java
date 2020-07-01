package me.sun.notification_service.core.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.web.notification.SlackTemplate;
import me.sun.notification_service.web.notification.SlackTemplateBuilder;
import me.sun.notification_service.web.notification.model.slack.dto.Attachment;
import me.sun.notification_service.web.notification.model.slack.dto.Field;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class ForecastMessage implements SlackTemplate {

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

    @Override
    public List<Attachment> createSlackAttachments(SlackTemplateBuilder builder) {
        return builder.build(this);
    }
}
