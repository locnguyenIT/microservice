package com.ntloc.client.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orders", url = "http://localhost:8030")
public interface OrdersClient {

    @PostMapping(path = "/api/v1/orders")
    OrdersResponse order(@RequestBody OrdersRequest ordersRequest);
}
