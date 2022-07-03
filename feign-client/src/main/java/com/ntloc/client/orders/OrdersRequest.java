package com.ntloc.client.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersRequest {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer amount;
}
