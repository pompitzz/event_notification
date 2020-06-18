package me.sun.notification_service.crawler.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import me.sun.notification_service.crawler.City;
import me.sun.notification_service.crawler.HtmlParser;
import me.sun.notification_service.crawler.weather.dto.request.ForecastRequestParam;
import me.sun.notification_service.crawler.weather.dto.request.ForecastTime;
import me.sun.notification_service.crawler.weather.dto.request.Location;
import me.sun.notification_service.crawler.weather.dto.response.ForecastResponse;
import me.sun.notification_service.service.http.ParameterBuilder;
import me.sun.notification_service.utils.UrlUtils;
import org.jsoup.nodes.Document;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomRestTemplate {
    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ParameterBuilder parameterBuilder = new ParameterBuilder();

    static {
        DefaultUriBuilderFactory handler = new DefaultUriBuilderFactory();
        handler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        restTemplate.setUriTemplateHandler(handler);
    }

    public static void main(String[] args) {
        final String path = "https://www.weather.go.kr/w/wnuri-fct/weather/today-vshortmid.do?code=1174062000&unit=km%2Fh";
//        final Document document = HtmlParser.get(path);
        new CustomRestTemplate().cityInfoMaker();
    }

    public void cityInfoMaker() {
        String key = "Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D";
        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
        ForecastRequestParam param = new ForecastRequestParam(key, LocalDate.now().minusDays(1), ForecastTime.findOptimalForecastTime(LocalTime.of(3, 0)), Location.A);
        String url = UrlUtils.buildUrl(baseUrl, parameterBuilder.buildParameter(param));
        List<ForecastResponse> body = getBody(url, ForecastResponse.class);
        System.out.println(body);
    }

    public <T> List<T> getBody(String url, Class<T> clazz) {
        return request(url, HttpMethod.GET, clazz).getBody();
    }

    public <T> ResponseEntity<List<T>> request(String url, HttpMethod method, Class<T> clazz) {
        return restTemplate.exchange(url, method, null,
                new ParameterizedTypeReference<List<T>>() {
                    @Override
                    public Type getType() {
                        return new CustomParameterizedType(List.class, clazz);
                    }
                });
    }


    private static class CustomParameterizedType implements ParameterizedType {

        private Class<?> rawType;
        private Type type;

        public CustomParameterizedType(Class<?> rawType, Type type) {
            if (rawType.getTypeParameters().length == 0) {
                throw new IllegalArgumentException("rawType은 TypeParameter가 존재해야 합니다.");
            }
            this.rawType = rawType;
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return rawType;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
