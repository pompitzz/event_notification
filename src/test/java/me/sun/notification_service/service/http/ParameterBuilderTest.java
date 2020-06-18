package me.sun.notification_service.service.http;

import me.sun.notification_service.Parameterizable;
import me.sun.notification_service.crawler.Parameter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ParameterBuilderTest {

    ParameterBuilder parameterBuilder = new ParameterBuilder();

    @Test
    void parameterizable()throws Exception {
        RequestParam param = new RequestParam("Dexter", 25, null, 1000);
        List<Parameter> parameters = parameterBuilder.buildParameter(param);

        assertThat(parameters.size()).isEqualTo(3);
        assertThat(parameters)
                .as("null인 데이터는 포함되지 않는다.")
                .extracting("key", "value")
                .contains(
                        tuple("name", "Dexter"),
                        tuple("age", "25"),
                        tuple("score", "1000")
                        );
    }



    static class RequestParam implements Parameterizable {
        String name;
        Integer age;
        Long id;
        int score;

        public RequestParam(String name, Integer age, Long id, int score) {
            this.name = name;
            this.age = age;
            this.id = id;
            this.score = score;
        }
    }
}