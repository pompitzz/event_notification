package me.sun.notification_service.runner;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.forecast.town.Town;
import me.sun.notification_service.core.domain.forecast.town.TownRepository;
import me.sun.reader.ReaderUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("dev")
@Order(0)
@RequiredArgsConstructor
public class TownCreateRunner implements ApplicationRunner {
    private final TownRepository townRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<Town> towns = ReaderUtils.readLines("/Users/dongmyeonglee/Procjects/notification_service/test.csv")
                .stream()
                .map(line -> line.split(","))
                .filter(tokens -> tokens.length == 5)
                .map(this::buildTown)
                .collect(Collectors.toList());

        townRepository.saveAll(towns);
    }

    private Town buildTown(String[] tokens) {
        return Town.builder()
                .state(tokens[0])
                .city(tokens[1])
                .addressDetail(tokens[2])
                .locationX(tokens[3])
                .locationY(tokens[4])
                .build();
    }
}
