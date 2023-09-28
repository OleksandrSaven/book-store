package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsByShoppingCartId(Long id);
}
