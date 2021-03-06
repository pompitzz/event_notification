package me.sun.notification_service.core.domain.forecast.forecast;

import lombok.*;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Forecast {

    @Id
    @Column(name = "forecast_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forecastId;

    @Enumerated(EnumType.STRING)
    private ForecastCategory forecastCategory;

    private LocalDate forecastDate;
    private LocalTime forecastTime;
    private String measureValue;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private ForecastLocation forecastLocation;

    public String diffMeasureValue(Forecast yesterday) {
        if (Objects.isNull(yesterday)) {
            return null;
        }

        final double todayValue = Double.parseDouble(this.measureValue);
        final double yesterdayValue = Double.parseDouble(yesterday.measureValue);
        return String.valueOf(todayValue - yesterdayValue);
    }
}
