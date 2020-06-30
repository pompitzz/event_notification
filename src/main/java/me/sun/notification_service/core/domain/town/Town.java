package me.sun.notification_service.core.domain.town;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Town {
    @Id
    @Column(name = "town_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long townId;
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
