package me.sun.notification_service.core.processor.forecast.builder;

import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.core.processor.forecast.model.MeasureValue;
import me.sun.notification_service.core.processor.forecast.model.TimeMeasureValue;
import me.sun.notification_service.infrastructure.utils.MessageUtils;
import me.sun.notification_service.web.notification.NotificationMessageBuilder;
import me.sun.notification_service.web.notification.slack.SlackArguments;
import me.sun.notification_service.web.notification.slack.model.Attachment;
import me.sun.notification_service.web.notification.slack.model.Field;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ForecastSlackMessageBuilder implements ForecastNotificationMessageBuilder<SlackArguments> {

    @Override
    public SlackArguments successMessageBuild(ForecastResult forecastResult) {
        final Attachment mainAttachment = Attachment.from(forecastResult.getTitle(), "#36a64f");
        final Attachment rainAttachment = rainAttachment(forecastResult.highestRainPercent());
        final Attachment measureAttachment = Attachment.from(measureFields(forecastResult.getTimeMeasureValues()), "#b61549");

        final List<Attachment> attachments = Arrays.asList(mainAttachment, rainAttachment, measureAttachment);
        return SlackArguments.builder()
                .token("xoxb-1175038918580-1169073439443-DxGp4cjXFZHBEzrehGLoPr2P")
                .channel("C014S3QJQ4E")
                .text("TEXT")
                .attachments(attachments)
                .build();
    }

    private Attachment rainAttachment(double highestRainPercent) {
        final String title = String.format("강수확률: %s%s", highestRainPercent, ForecastCategory.POP.getUnit());
        return Attachment.from(title, "#1a6d9e");
    }

    private List<Field> measureFields(List<TimeMeasureValue> timeMeasureValues) {
        return timeMeasureValues.stream()
                .map(this::buildMeasureField)
                .collect(Collectors.toList());
    }

    private Field buildMeasureField(TimeMeasureValue timeMeasureValue) {
        final String title = "시간: " + timeMeasureValue.getTime();
        final List<MeasureValue> isNotRainValues = timeMeasureValue.getMeasureValues().stream()
                .filter(MeasureValue::isNotRainCategory)
                .collect(Collectors.toList());

        final String value = MessageUtils.joining("\n", isNotRainValues, MeasureValue::toMessage);
        return Field.builder()
                .title(title)
                .value(value)
                .build();
    }

    @Override
    public SlackArguments failMessageBuild(ForecastLocation forecastLocation) {
        // TODO 체인지 필요
        return null;
    }
}
