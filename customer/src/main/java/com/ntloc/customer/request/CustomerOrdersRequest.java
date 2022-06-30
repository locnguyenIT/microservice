package com.ntloc.customer.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CustomerOrdersRequest {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer amount;
    private LocalDateTime createAt;
}
