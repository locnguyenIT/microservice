package com.ntloc.customer.feign;

import com.ntloc.customer.request.CustomerOrdersRequest;
import com.ntloc.customer.response.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orders", url = "http://localhost:8030")
public interface CustomerOrdersClient {

    @PostMapping(path = "/api/v1/orders")
    OrdersResponse order(@RequestBody CustomerOrdersRequest ordersResponse);
}
