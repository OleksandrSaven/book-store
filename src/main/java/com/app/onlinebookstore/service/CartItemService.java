package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.UpdateCartItemDto;
import java.util.List;

public interface CartItemService {
    CartItemDto save(CreateCartItemRequestDto requestDto);

    CartItemDto update(Long id, UpdateCartItemDto updateCartItemDto);

    List<CartItemDto> findCartItemsByShoppingCarts(Long id);

    void delete(Long id);
}
