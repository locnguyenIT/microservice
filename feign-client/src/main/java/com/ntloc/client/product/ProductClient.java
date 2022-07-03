package com.ntloc.client.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product", url = "http://localhost:8020")
public interface ProductClient {

    @GetMapping(path = "/api/v1/product/{productId}")
    ProductResponse getProduct(@PathVariable("productId") Long productId);
}
