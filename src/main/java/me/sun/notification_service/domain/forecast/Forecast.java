package me.sun.notification_service.domain.forecast;

import lombok.*;
import me.sun.notification_service.crawler.forecast.model.ForecastCategory;
import me.sun.notification_service.domain.town.Town;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private Town town;

    public String diffMeasureValue(Forecast yesterday) {
        final double todayValue = Double.parseDouble(this.measureValue);
        final double yesterdayValue = Double.parseDouble(yesterday.measureValue);
        return String.valueOf(todayValue - yesterdayValue);
    }
}
