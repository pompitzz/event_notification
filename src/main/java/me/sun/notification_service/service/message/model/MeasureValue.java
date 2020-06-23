package me.sun.notification_service.service.message.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.crawler.forecast.model.ForecastCategory;

@Getter
@Builder
@AllArgsConstructor
public class MeasureValue {
    private ForecastCategory category;
    private String measureValue;
    private String diffValueFromYesterday;

    public String toMessage() {
        final String message = String.format(
                "%s: %s%s",
                this.category.getDescription(),
                this.measureValue,
                this.category.getUnit());

        if (isRainCategory()) {
            return String.format("%s (%s)", message, this.diffValueFromYesterday);
        }

        return message;
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
