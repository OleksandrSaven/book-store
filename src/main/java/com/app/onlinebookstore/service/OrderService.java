package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.CreateOrderRequestDto;
import com.app.onlinebookstore.dto.OrderDto;
import com.app.onlinebookstore.dto.OrderItemDto;
import com.app.onlinebookstore.dto.OrderStatusDto;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto createOrder(CreateOrderRequestDto requestDto, Long id);

    List<OrderDto> findAllOrders(Pageable pageable);

    Set<OrderItemDto> findAllOrderItems(Long orderId);

    OrderItemDto findOrderItemById(Long orderId, Long itemId);

    OrderDto changeStatus(Long orderId, OrderStatusDto request);
}
