package org.example.timestampapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableScheduling
@SpringBootApplication
public class TimeStampAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeStampAppApplication.class, args);
    }

}
