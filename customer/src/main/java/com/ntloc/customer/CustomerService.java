package com.ntloc.customer;

import com.ntloc.customer.feign.CustomerOrdersFeignClient;
import com.ntloc.customer.feign.CustomerProductFeignClient;
import com.ntloc.customer.request.CustomerOrdersRequest;
import com.ntloc.customer.response.CustomerOrdersResponse;
import com.ntloc.customer.response.OrdersResponse;
import com.ntloc.customer.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerProductFeignClient customerProductFeignClient;
    private final CustomerOrdersFeignClient customerOrdersFeignClient;

    public List<CustomerDTO> getAllCustomer() {
        List<CustomerEntity> listCustomer = customerRepository.findAll();
        return customerMapper.toListDTO(listCustomer);
    }

    public CustomerDTO getCustomer(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() ->
                new IllegalStateException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    public CustomerDTO register(CustomerDTO customerDTO) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();
        CustomerEntity save = customerRepository.save(customer);
        log.info("Customer register success {}", save);
        return customerMapper.toDTO(save);
    }

    public CustomerOrdersResponse orders(CustomerOrdersRequest customerOrdersRequest) {
        CustomerEntity customer = customerRepository.findById(customerOrdersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException("Customer not found"));

        OrdersResponse ordersResponse = customerOrdersFeignClient.order(customerOrdersRequest);

        ProductResponse productResponse = customerProductFeignClient.getProduct(ordersResponse.getProductId());

        CustomerOrdersResponse customerOrdersResponse = CustomerOrdersResponse.builder()
                .id(ordersResponse.getId())
                .customer(customerMapper.toDTO(customer))
                .product(productResponse)
                .amount(ordersResponse.getAmount())
                .createAt(ordersResponse.getCreateAt()).build();
        return customerOrdersResponse;

    }

}
