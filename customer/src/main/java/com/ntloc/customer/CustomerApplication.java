package com.ntloc.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//ScanBasePackages is scan package to fetch all class from that package
@SpringBootApplication(
        scanBasePackages = {
                "com.ntloc.amqp",
                "com.ntloc.customer"
        }
)
@EnableEurekaClient
//basePackages to fetch all @FeignClient from that package
@EnableFeignClients(
        basePackages = "com.ntloc.client"
)
//PropertySources to use properties file of feign-client to run different environments (local, docker)
@PropertySources({
        @PropertySource("classpath:client-${spring.profiles.active}.properties")
})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
