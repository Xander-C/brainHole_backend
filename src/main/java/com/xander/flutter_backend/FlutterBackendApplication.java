package com.xander.flutter_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlutterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlutterBackendApplication.class, args);
    }

}
