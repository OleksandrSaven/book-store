package com.app.onlinebookstore.service.impl;

import com.app.onlinebookstore.dto.CreateOrderRequestDto;
import com.app.onlinebookstore.dto.OrderDto;
import com.app.onlinebookstore.dto.OrderItemDto;
import com.app.onlinebookstore.dto.OrderStatusDto;
import com.app.onlinebookstore.exaption.EntityNotFoundException;
import com.app.onlinebookstore.mapper.OrderItemMapper;
import com.app.onlinebookstore.mapper.OrderMapper;
import com.app.onlinebookstore.model.CartItem;
import com.app.onlinebookstore.model.Order;
import com.app.onlinebookstore.model.OrderItem;
import com.app.onlinebookstore.model.ShoppingCart;
import com.app.onlinebookstore.model.Status;
import com.app.onlinebookstore.model.User;
import com.app.onlinebookstore.repository.OrderItemRepository;
import com.app.onlinebookstore.repository.OrderRepository;
import com.app.onlinebookstore.repository.ShoppingCartRepository;
import com.app.onlinebookstore.service.OrderService;
import com.app.onlinebookstore.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Override
    public OrderDto createOrder(CreateOrderRequestDto requestDto, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository
                 .findByUserId(currentUser.getId()).orElseThrow(
                        () -> new EntityNotFoundException("Can't find shopping cart by user id "
                                + currentUser.getId()));
        Order order = new Order();
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setUser(currentUser);
        order.setTotal(shoppingCart.getCartItems().stream()
                .map(this::getTotalPrice)
                .reduce(BigDecimal::add).orElseThrow());
        Order savedOrder = orderRepository.save(order);
        order.setOrderItems(shoppingCart.getCartItems().stream()
                .map(i -> orderItemMapper.cartItemToOrderItem(i, savedOrder))
                .map(orderItemRepository::save)
                .collect(Collectors.toSet()));
        return setOrderItems(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> findAllOrders(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        List<Order> orders = orderRepository.getOrderByUserId(user.getId());
        for (Order order : orders) {
            order.setOrderItems(orderItemRepository.getAllByOrderId(order.getId()));
        }
        return orders.stream()
                .map(this::setOrderItems)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OrderItemDto> findAllOrderItems(Long orderId) {
        User user = userService.getAuthenticatedUser();
        Set<OrderItem> orderItems = orderRepository.getOrderByUserId(user.getId()).stream()
                .filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(
                        () -> new EntityNotFoundException("Can't find order by id " + orderId))
                .getOrderItems();
        return orderItems.stream().map(orderItemMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public OrderItemDto findOrderItemById(Long orderId, Long itemId) {
        return orderRepository.getOrderByUserId(userService.getAuthenticatedUser().getId()).stream()
                .filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(() ->
                        new EntityNotFoundException("Can't find order by id " + orderId))
                .getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .filter(item -> item.getId().equals(itemId))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException("Can't find item by id " + itemId));
    }

    @Override
    public OrderDto changeStatus(Long orderId, OrderStatusDto request) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id " + orderId));
        order.setStatus(request.getStatus());
        return setOrderItems(order);
    }

    private OrderDto setOrderItems(Order order) {
        OrderDto orderDto = orderMapper.toDto(order);
        orderDto.setOrderItems(orderItemRepository.getAllByOrderId(order.getId()).stream()
                .map(orderItemMapper::toDto).collect(Collectors.toSet()));
        return orderDto;
    }

    private BigDecimal getTotalPrice(CartItem cartItem) {
        return cartItem.getBook().getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
