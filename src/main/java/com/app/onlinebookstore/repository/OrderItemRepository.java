package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.OrderItem;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> getAllByOrderId(Long id);

    @Query("FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.order.user.id = :userId ")
    Set<OrderItem> findOrderItemsByOrderIdAndUserId(Long orderId, Long userId);

    @Query("FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.id = :orderItemId "
            + "AND oi.order.user.id = :userId ")
    Optional<OrderItem> findOrderItemByOrderIdIdAndOrderItemIdAndUser(
            Long orderId, Long orderItemId, Long userId);
}
