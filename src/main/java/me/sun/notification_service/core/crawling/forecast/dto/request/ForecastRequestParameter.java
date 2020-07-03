package me.sun.notification_service.core.crawling.forecast.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.infrastructure.Parameterizable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class ForecastRequest implements Parameterizable {
    private String serviceKey;
    private Integer pageNo = 1;
    private Integer numOfRows = 100;
    private String dataType = "JSON";
    private String base_date;
    private String base_time;
    private String nx;
    private String ny;
}
