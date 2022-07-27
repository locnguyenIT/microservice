package com.ntloc.notification;

import com.ntloc.client.notification.NotificationRequest;
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

    public NotificationDTO getNotification(Long notificationId) {
        NotificationEntity product = notificationRepository.findById(notificationId).orElseThrow(() ->
                new IllegalStateException("Notification not found"));
        return notificationMapper.toDTO(product);
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        NotificationEntity notification = notificationRepository.save(NotificationEntity.builder()
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerName(notificationRequest.getToCustomerName())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .sender("ntloc")
                .message(notificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .build());

    }
}
