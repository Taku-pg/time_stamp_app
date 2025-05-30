package org.example.timestampapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TimeStampAppApplication {

    public static void main(String[] args) {
        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "EmployeeNo3";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed password: " + hashedPassword);*/

        SpringApplication.run(TimeStampAppApplication.class, args);

    }

}
