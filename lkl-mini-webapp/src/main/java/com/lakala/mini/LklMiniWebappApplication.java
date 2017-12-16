package com.lakala.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.lakala")
@ImportResource(locations={"classpath:spring/ctx_main.xml"})
public class LklMiniWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(LklMiniWebappApplication.class, args);
    }
}
