package com.ntloc.client.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProductResponse {

    private Long id;
    private String name;
    private String image;
    private Integer price;
}
