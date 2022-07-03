package com.ntloc.notification.rabbitmq;

import com.ntloc.customer.request.CustomerNotificationRequest;
import com.ntloc.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(CustomerNotificationRequest customerNotificationRequest) {
        log.info("Consumed {} from queue", customerNotificationRequest);
        notificationService.sendNotification(customerNotificationRequest);
    }
}
