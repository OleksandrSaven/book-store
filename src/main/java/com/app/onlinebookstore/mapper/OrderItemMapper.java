package com.app.onlinebookstore.mapper;

import com.app.onlinebookstore.config.MapperConfig;
import com.app.onlinebookstore.dto.OrderItemDto;
import com.app.onlinebookstore.model.CartItem;
import com.app.onlinebookstore.model.Order;
import com.app.onlinebookstore.model.OrderItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    OrderItem cartItemToOrderItem(CartItem cartItem, Order order);

    @AfterMapping
    default void setOrderInItem(@MappingTarget OrderItem orderItem,
                                CartItem cartItem, Order order) {
        orderItem.setOrder(order);
        orderItem.setBook(cartItem.getBook());
        orderItem.setPrice(cartItem.getBook().getPrice());
    }

    OrderItemDto toDto(OrderItem orderItem);

    @AfterMapping
    default void setBookId(@MappingTarget OrderItemDto orderItemDto, OrderItem orderItem) {
        orderItemDto.setBookId(orderItem.getBook().getId());
    }
}
