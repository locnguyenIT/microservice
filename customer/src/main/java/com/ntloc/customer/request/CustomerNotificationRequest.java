package com.ntloc.customer.request;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerNotificationRequest {

    private Long toCustomerId;
    private String toCustomerName;
    private String toCustomerEmail;
    private String sender;
    private String message;
}
