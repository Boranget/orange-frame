package com.orange.frame.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class OrangeFrameHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeFrameHelloApplication.class, args);
    }

}
