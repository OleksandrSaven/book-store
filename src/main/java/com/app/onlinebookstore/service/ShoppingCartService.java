package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.ShoppingCartDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    CartItemDto save(CreateCartItemRequestDto requestDto);

    ShoppingCartDto getShoppingCart(Authentication authentication, Pageable pageable);
}
