package com.ntloc.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "http://localhost:8040")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notification")
    NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);

}
