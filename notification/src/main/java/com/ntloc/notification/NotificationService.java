package com.ntloc.notification;

import com.ntloc.customer.request.CustomerNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationDTO> getAllNotification() {
        List<NotificationEntity> listProduct = notificationRepository.findAll();
        return notificationMapper.toListDTO(listProduct);
    }

    public NotificationDTO getNotification(Long otificationId) {
        NotificationEntity product = notificationRepository.findById(otificationId).orElseThrow(() ->
                new IllegalStateException("Notification not found"));
        return notificationMapper.toDTO(product);
    }

    public void sendNotification(CustomerNotificationRequest customerNotificationRequest) {
        NotificationEntity notification = notificationRepository.save(NotificationEntity.builder()
                .toCustomerId(customerNotificationRequest.getToCustomerId())
                .toCustomerName(customerNotificationRequest.getToCustomerName())
                .toCustomerEmail(customerNotificationRequest.getToCustomerEmail())
                .sender("ntloc")
                .message(customerNotificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .build());

    }
}
