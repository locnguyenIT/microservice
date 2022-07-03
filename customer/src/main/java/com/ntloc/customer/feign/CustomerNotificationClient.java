package com.ntloc.customer.feign;

import com.ntloc.customer.request.CustomerNotificationRequest;
import com.ntloc.customer.response.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "http://localhost:8040")
public interface CustomerNotificationClient {

    @PostMapping(path = "/api/v1/notification")
    NotificationResponse sendNotification(@RequestBody CustomerNotificationRequest customerNotificationRequest);
}
