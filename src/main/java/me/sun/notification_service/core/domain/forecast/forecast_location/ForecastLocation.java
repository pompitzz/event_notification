package me.sun.notification_service.core.domain.forecast.forecast_location;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ForecastLocation {
    @Id
    @Column(name = "forecast_location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forecastLocationId;
    private String state;
    private String city;
    private String addressDetail;

    @Column(name = "location_x")
    private String locationX;

    @Column(name = "location_y")
    private String locationY;

    public String getFullAddress() {
        return String.format("%s %s %s", state, city, addressDetail);
    }
}
