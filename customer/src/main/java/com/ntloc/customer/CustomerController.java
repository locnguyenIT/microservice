package com.ntloc.customer;

import com.ntloc.customer.request.CustomerOrdersRequest;
import com.ntloc.customer.response.CustomerOrdersResponse;
import com.ntloc.customer.response.CustomerRegistrationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping(path = "/{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping(path = "/registration")
    public CustomerRegistrationResponse register(@RequestBody CustomerDTO customerDTO) {
        log.info("New customer registration {}", customerDTO);
        return customerService.register(customerDTO);
    }

    @PostMapping(path = "/orders")
    public CustomerOrdersResponse orders(@RequestBody CustomerOrdersRequest customerOrdersRequest) {
        log.info("Customer orders {}", customerOrdersRequest);
        return customerService.orders(customerOrdersRequest);
    }

}
