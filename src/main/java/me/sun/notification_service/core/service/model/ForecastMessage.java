package me.sun.notification_service.core.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
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
public class ForecastMessage {
    private LocalDate forecastDate;
    private String locationInformation;
    private List<TimeMeasureValue> timeMeasureValues;

    public String toMessage() {
        final String timeMeasureValuesMessage = timeMeasureValues.stream()
                .map(TimeMeasureValue::toMessage)
                .collect(Collectors.joining("\n"));

        return String.format(
                "========================%s=======================\n" +
                        "날짜: %s\n" +
                        "%s" +
                        "================================================",
                locationInformation,
                forecastDate,
                timeMeasureValuesMessage
        );
    }

    public List<Attachment> toAttachments() {
        String title = String.format("%s | %s", locationInformation, forecastDate);
        final Attachment mainAttachemnt = Attachment.builder()
                .title(title)
//                .title_link("http://naver.com")
                .color("#36a64f")
                .build();

        final List<Field> fields = timeMeasureValues.stream()
                .map(TimeMeasureValue::toField)
                .collect(Collectors.toList());
        final Attachment measureAttachment = Attachment.build(fields, "#b61549");

        return Arrays.asList(mainAttachemnt, rainAttachment(), measureAttachment);
    }
    private Attachment rainAttachment(){
        final List<MeasureValue> rainMeasureValue = this.timeMeasureValues.stream()
                .map(TimeMeasureValue::getMeasureValues)
                .flatMap(Collection::stream)
                .filter(MeasureValue::isRainCategory)
                .collect(Collectors.toList());
        double value = 0;
        for (MeasureValue measureValue : rainMeasureValue) {
            value = measureValue.getHigher(value);
        }

        return Attachment.builder()
                .title(String.format("강수확률: %s%s", value, ForecastCategory.POP.getUnit()))
                .color("#1a6d9e")
                .build();
    }
}
