package com.ntloc.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDTO register(CustomerDTO customerDTO) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();
        CustomerEntity save = customerRepository.save(customer);
        log.info("Customer register success {}", save);
        return customerMapper.toDTO(save);
    }

}
