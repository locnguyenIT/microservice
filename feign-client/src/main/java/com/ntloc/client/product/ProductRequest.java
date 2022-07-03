package com.ntloc.client.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequest {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer amount;
}
