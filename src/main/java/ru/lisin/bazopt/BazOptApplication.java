package ru.lisin.bazopt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BazOptApplication {
    public static void main(String[] args) {
        SpringApplication.run(BazOptApplication.class, args);
    }
}