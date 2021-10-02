package com.example.wiremockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WiremockDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiremockDemoApplication.class, args);
    }

}
