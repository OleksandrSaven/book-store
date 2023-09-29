package com.app.onlinebookstore.controller;

import com.app.onlinebookstore.dto.CreateOrderRequestDto;
import com.app.onlinebookstore.dto.OrderDto;
import com.app.onlinebookstore.dto.OrderItemDto;
import com.app.onlinebookstore.dto.OrderStatusDto;
import com.app.onlinebookstore.service.OrderService;
import com.app.onlinebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Operation(summary = "Create new order",
            description = "Return created order")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {
        Long userId = userService.getAuthenticatedUser().getId();
        return orderService.createOrder(requestDto, userId);
    }

    @Operation(summary = "Get all orders", description = "Get a list of all available orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<OrderDto> findAllOrder(Pageable pageable) {
        return orderService.findAllOrders(pageable);
    }

    @Operation(summary = "Get all items from order",
            description = "Get a list of all items from order")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{orderId}/items")
    public Set<OrderItemDto> findAllItems(@PathVariable Long orderId) {
        return orderService.findAllOrderItems(orderId);
    }

    @Operation(summary = "Get an order by item", description = "Get an order item from order")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{orderId}/items/{itemId}")
    public OrderItemDto findOrderItemById(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.findOrderItemById(orderId, itemId);
    }

    @Operation(summary = "Change order status", description = "Change order status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public OrderDto updateStatus(@PathVariable Long id,
                                 @RequestBody @Valid OrderStatusDto request) {
        return orderService.changeStatus(id, request);
    }
}
