package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsByShoppingCartId(Long id);

    @Query("FROM CartItem ci WHERE ci.id = :itemId AND ci.shoppingCart.user.id = :userId ")
    CartItem findCartItemsByCartIdAndUserId(Long itemId, Long userId);
}
