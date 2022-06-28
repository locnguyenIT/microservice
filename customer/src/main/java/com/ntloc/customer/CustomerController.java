package com.ntloc.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "/registration")
    public CustomerDTO register(@RequestBody CustomerDTO customerDTO) {
        log.info("New customer registration {}", customerDTO);
        return customerService.register(customerDTO);
    }

}
