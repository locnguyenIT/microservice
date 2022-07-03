package com.ntloc.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//ScanBasePackages is using to load all the bean from that package
@SpringBootApplication(
        scanBasePackages = {"com.ntloc.amqp",
                "com.ntloc.customer"})
@EnableEurekaClient
@EnableFeignClients
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
