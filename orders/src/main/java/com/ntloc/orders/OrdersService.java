package com.ntloc.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> allOrders = ordersRepository.findAll();
        return ordersMapper.toListDTO(allOrders);
    }

    public OrdersDTO getOrders(Long ordersId) {
        OrdersEntity orders = ordersRepository.findById(ordersId).orElseThrow(() ->
                new IllegalStateException("Orders not found"));
        return ordersMapper.toDTO(orders);
    }

    public OrdersDTO order(OrdersDTO ordersDTO) {
        OrdersEntity ordersEntity = ordersMapper.toEntity(ordersDTO);
        ordersEntity.setCreateAt(LocalDateTime.now());
        return ordersMapper.toDTO(ordersRepository.save(ordersEntity));
    }

}
