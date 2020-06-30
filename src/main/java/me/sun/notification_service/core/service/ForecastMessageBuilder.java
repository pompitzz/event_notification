package me.sun.notification_service.core.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.Forecast;
import me.sun.notification_service.core.service.model.ForecastMessage;
import me.sun.notification_service.core.service.model.MeasureValue;
import me.sun.notification_service.core.service.model.TimeMeasureValue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class ForecastMessageBuilder {
    public ForecastMessage build(List<Forecast> yesterdayForecasts, List<Forecast> todayForecasts) {
        final Forecast forecast = todayForecasts.get(0);
        final String locationInformation = forecast.getTown().getFullAddress();
        final LocalDate date = forecast.getForecastDate();

        return ForecastMessage.builder()
                .locationInformation(locationInformation)
                .forecastDate(date)
                .timeMeasureValues(buildDetailMessage(yesterdayForecasts, todayForecasts))
                .build();
    }

    private List<TimeMeasureValue> buildDetailMessage(List<Forecast> yesterdayForecasts, List<Forecast> todayForecasts) {
        final Map<LocalTime, List<Forecast>> forecastGroupingByTime = Stream.of(yesterdayForecasts, todayForecasts)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Forecast::getForecastTime));

        return forecastGroupingByTime.values()
                .stream()
                .map(this::buildMessage)
                .sorted(Comparator.comparing(TimeMeasureValue::getTime))
                .collect(Collectors.toList());
    }

    private TimeMeasureValue buildMessage(List<Forecast> forecasts) {
        final Map<ForecastCategory, List<Forecast>> forecastGroupingByCategory = forecasts.stream()
                .collect(Collectors.groupingBy(Forecast::getForecastCategory));

        final List<MeasureValue> measureValues = forecastGroupingByCategory.values()
                .stream()
                .filter(this::filteredOnlyHasYesterdayAndToday)
                .map(this::sortAndBuild)
                .map(this::buildMessage)
                .collect(Collectors.toList());

        return TimeMeasureValue.builder()
                .time(forecasts.get(0).getForecastTime())
                .measureValues(measureValues)
                .build();
    }

    private MeasureValue buildMessage(ForecastWrapper forecastWrapper) {
        final Forecast today = forecastWrapper.getToday();

        final ForecastCategory forecastCategory = today.getForecastCategory();
        final String measureValue = today.getMeasureValue();

        final MeasureValue.MeasureValueBuilder builder = MeasureValue.builder()
                .category(forecastCategory)
                .measureValue(measureValue);

        if (ForecastCategory.isNotRain(forecastCategory)) {
            final String diffValue = today.diffMeasureValue(forecastWrapper.getYesterday());
            builder.diffValueFromYesterday(diffValue);
        }

        return builder.build();
    }

    private boolean filteredOnlyHasYesterdayAndToday(List<Forecast> forecasts) {
        if (forecasts.size() != 2) {
            final List<Long> ids = forecasts.stream().map(Forecast::getForecastId).collect(Collectors.toList());
            log.error("Forecast는 두개만 존재해야 한다. forecastIds: {}", ids);
            return false;
        }
        return true;
    }

    private ForecastWrapper sortAndBuild(List<Forecast> forecasts) {
        forecasts.sort(Comparator.comparing(Forecast::getForecastDate));
        return new ForecastWrapper(forecasts.get(0), forecasts.get(1));
    }


    @Getter
    @AllArgsConstructor
    private static class ForecastWrapper {
        private Forecast yesterday;
        private Forecast today;
    }
}
