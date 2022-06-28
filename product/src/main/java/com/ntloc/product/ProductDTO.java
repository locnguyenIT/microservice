package com.ntloc.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

    private Long id;
    private String name;
    private String image;
    private Integer price;
}
