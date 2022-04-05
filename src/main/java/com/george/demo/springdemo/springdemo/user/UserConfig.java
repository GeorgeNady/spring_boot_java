package com.george.demo.springdemo.springdemo.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User user1 = userGenerator("george");
            User user2 = userGenerator("joseph");
            User user3 = userGenerator("caroline");

            ArrayList<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);
            users.add(user3);

            repository.saveAll(users);
        };
    }

    @org.jetbrains.annotations.Contract("_ -> new")
    private @NotNull User userGenerator(String name) {
        return new User(
                name,
                name + "@gmail.com",
                phoneGenerator(),
                localDateGenerator(),
                "ssdfasdfasdscascaweawecasdcasdc"
        );
    }

    private @NotNull String phoneGenerator() {
        ArrayList<String> providers = new ArrayList<>();
        providers.add("010");
        providers.add("011");
        providers.add("012");
        providers.add("015");

        Random rand = new Random();
        int rand_number = rand.nextInt(99999999);
        int rand_provider = rand.nextInt(4);

        return providers.get(rand_provider) + (rand_number);
    }

    private @NotNull LocalDate localDateGenerator() {
        Random rand = new Random();

        int rand_year = Integer.parseInt("19" + rand.nextInt(100));
        int rand_month = rand.nextInt(12);
        int rand_day = rand.nextInt(29);

        return LocalDate.of(rand_year, rand_month, rand_day);
    }

}
