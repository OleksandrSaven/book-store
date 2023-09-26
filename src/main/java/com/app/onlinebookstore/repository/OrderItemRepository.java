package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.OrderItem;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> getAllByOrderId(Long id);
}
