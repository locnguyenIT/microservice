package com.ntloc.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//ScanBasePackages is using to load all the bean from that package
@SpringBootApplication(
        scanBasePackages = {
                "com.ntloc.notification",
                "com.ntloc.amqp",
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
