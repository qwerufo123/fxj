package com.fxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FxjApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxjApplication.class, args);
    }

}
