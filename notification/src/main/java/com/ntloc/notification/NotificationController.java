package com.ntloc.notification;

import com.ntloc.customer.request.CustomerNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.notification.NotificationConstant.URI_REST_API_VERSION_NOTIFICATION;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_NOTIFICATION)
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotification() {
        return notificationService.getAllNotification();
    }

    @GetMapping(path = "/{notificationId}")
    public NotificationDTO getNotification(@PathVariable("notificationId") Long notificationId) {
        log.info("NotificationId {}", notificationId);
        return notificationService.getNotification(notificationId);
    }

    @PostMapping
    public void sendNotification(@RequestBody CustomerNotificationRequest customerNotificationRequest) {
        log.info("New notification... {}", customerNotificationRequest);
        notificationService.sendNotification(customerNotificationRequest);
    }

}
