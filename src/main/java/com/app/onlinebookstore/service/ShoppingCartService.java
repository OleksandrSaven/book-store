package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.ShoppingCartDto;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    CartItemDto save(CreateCartItemRequestDto requestDto);

    ShoppingCartDto getShoppingCart(Pageable pageable);
}
