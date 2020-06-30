package me.sun.notification_service.runner;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.town.Town;
import me.sun.notification_service.core.domain.town.TownRepository;
import me.sun.reader.ReaderUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("!prod")
@Order(0)
@RequiredArgsConstructor
public class CreateTown implements ApplicationRunner {
    private final TownRepository townRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> lines = ReaderUtils.readLines("/Users/dongmyeonglee/Procjects/notification_service/test.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length == 5) {
                Town town = Town.builder()
                        .state(tokens[0])
                        .city(tokens[1])
                        .addressDetail(tokens[2])
                        .locationX(tokens[3])
                        .locationY(tokens[4])
                        .build();
                townRepository.save(town);
            }
        }
    }
}
