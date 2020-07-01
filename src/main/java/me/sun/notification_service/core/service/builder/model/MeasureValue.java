package me.sun.notification_service.core.service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.forecast.Forecast;
import org.springframework.util.StringUtils;

@Getter
@Builder
@AllArgsConstructor
public class MeasureValue {
    private ForecastCategory category;
    private String measureValue;
    private String diffValueFromYesterday;

    public String toMessage() {
        final StringBuilder message = new StringBuilder()
                .append(category.getDescription())
                .append(":")
                .append(measureValue)
                .append(category.getUnit());

        if (!ForecastCategory.isRain(category) && !StringUtils.isEmpty(diffValueFromYesterday)) {
            message.append("(")
                    .append(diffValueFromYesterday)
                    .append(")");
        }


        return message.toString();
    }

    public boolean isRainCategory() {
        return ForecastCategory.isRain(category);
    }

    public boolean isNotRainCategory() {
        return !isRainCategory();
    }

    public double getHigher(double yourValue) {
        final double myValue = Double.parseDouble(this.measureValue);
        return Math.max(myValue, yourValue);
    }
}
