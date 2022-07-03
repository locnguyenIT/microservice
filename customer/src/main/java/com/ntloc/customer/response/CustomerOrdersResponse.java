package com.ntloc.customer.response;

import com.ntloc.customer.CustomerDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrdersResponse {

    private Long id;
    private CustomerDTO customer;
    private ProductResponse product;
    private Integer amount;
    private LocalDateTime createAt;
    private NotificationResponse notification;
}
