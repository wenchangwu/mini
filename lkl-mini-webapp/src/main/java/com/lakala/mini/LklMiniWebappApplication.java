package com.lakala.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LklMiniWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(LklMiniWebappApplication.class, args);
    }
}
