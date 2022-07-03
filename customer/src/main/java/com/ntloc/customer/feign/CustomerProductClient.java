package com.ntloc.customer.feign;

import com.ntloc.customer.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://localhost:8020")
public interface CustomerProductClient {

    @GetMapping(path = "/api/v1/product/{productId}")
    ProductResponse getProduct(@PathVariable("productId") Long productId);
}
