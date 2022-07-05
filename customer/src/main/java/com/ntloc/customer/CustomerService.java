package com.ntloc.customer;


import com.ntloc.amqp.RabbitMQProducer;
import com.ntloc.client.notification.NotificationRequest;
import com.ntloc.client.orders.OrdersClient;
import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.orders.OrdersResponse;
import com.ntloc.client.product.ProductClient;
import com.ntloc.client.product.ProductResponse;
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
    private final OrdersClient ordersClient;
    private final ProductClient productClient;
    private final RabbitMQProducer rabbitMQProducer;

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
        //1. Save customer
        CustomerEntity customer = customerRepository.save(CustomerEntity.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build());
        //2. Create notificationRequest
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerName(customer.getName())
                .toCustomerEmail(customer.getEmail())
                .message(String.format("Hi %s. Welcome to PJ-AT microservices project", customer.getName()))
                .build();
        //3. Send notification to message queue
        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
        log.info("Customer register success {}", customer);
        //4. Return
        return customerMapper.toDTO(customer);
    }

    public OrdersResponse orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException("Customer not found"));

        ProductResponse product = productClient.getProduct(ordersRequest.getProductId());

        OrdersResponse order = ordersClient.order(ordersRequest);

        return order;

    }

}
