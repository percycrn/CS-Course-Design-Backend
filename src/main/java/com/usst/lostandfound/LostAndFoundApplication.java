package com.usst.lostandfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.usst.lostandfound.entity", "com.usst.lostandfound.controller",
        "com.usst.lostandfound.request", "com.usst.lostandfound.repository"})
public class LostAndFoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostAndFoundApplication.class, args);
    }
}
