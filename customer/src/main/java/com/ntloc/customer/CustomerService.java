package com.ntloc.customer;


import com.ntloc.amqp.RabbitMQProducer;
import com.ntloc.customer.feign.CustomerNotificationClient;
import com.ntloc.customer.feign.CustomerOrdersClient;
import com.ntloc.customer.feign.CustomerProductClient;
import com.ntloc.customer.request.CustomerNotificationRequest;
import com.ntloc.customer.request.CustomerOrdersRequest;
import com.ntloc.customer.response.CustomerOrdersResponse;
import com.ntloc.customer.response.CustomerRegistrationResponse;
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
    private final CustomerProductClient customerProductClient;
    private final CustomerOrdersClient customerOrdersClient;
    private final CustomerNotificationClient customerNotificationClient;
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

    public CustomerRegistrationResponse register(CustomerDTO customerDTO) {
        //1. Save customer
        CustomerEntity customer = customerRepository.save(CustomerEntity.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build());
        //2. Send notification to notification microservice
        CustomerNotificationRequest notificationRequest = CustomerNotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerName(customer.getName())
                .toCustomerEmail(customer.getEmail())
                .sender("ntloc")
                .message(String.format("Hi %s. Welcome to PJ-AT microservices project", customer.getName()))
                .build();
        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);

        //NotificationResponse notificationResponse = customerNotificationClient.sendNotification(notificationRequest);
        //System.out.println(notificationResponse);
        //3. Build CustomerRegistrationResponse
        CustomerRegistrationResponse customerRegistrationResponse = CustomerRegistrationResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
        log.info("Customer register success {}", customer);
        //4. Return
        return customerRegistrationResponse;
    }

    public CustomerOrdersResponse orders(CustomerOrdersRequest customerOrdersRequest) {
        CustomerEntity customer = customerRepository.findById(customerOrdersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException("Customer not found"));

        OrdersResponse ordersResponse = customerOrdersClient.order(customerOrdersRequest);

        ProductResponse productResponse = customerProductClient.getProduct(ordersResponse.getProductId());

        CustomerOrdersResponse customerOrdersResponse = CustomerOrdersResponse.builder()
                .id(ordersResponse.getId())
                .customer(customerMapper.toDTO(customer))
                .product(productResponse)
                .amount(ordersResponse.getAmount())
                .createAt(ordersResponse.getCreateAt()).build();
        return customerOrdersResponse;

    }

}
