package com.ntloc.orders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.orders.OrdersConstant.URI_REST_API_VERSION_ORDERS;

@AllArgsConstructor
@RestController
@RequestMapping(path = URI_REST_API_VERSION_ORDERS)
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping(path = "/{ordersId}")
    public OrdersDTO getOrders(@PathVariable("ordersId") Long ordersId) {
        return ordersService.getOrders(ordersId);
    }

    @PostMapping
    public OrdersDTO order(@RequestBody OrdersDTO ordersDTO) {
        return ordersService.order(ordersDTO);
    }

}
