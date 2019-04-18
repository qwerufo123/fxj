package com.fxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.fxj")
public class FxjApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxjApplication.class, args);
    }

}
