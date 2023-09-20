package com.app.onlinebookstore.service.impl;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.ShoppingCartDto;
import com.app.onlinebookstore.exaption.EntityNotFoundException;
import com.app.onlinebookstore.model.ShoppingCart;
import com.app.onlinebookstore.model.User;
import com.app.onlinebookstore.repository.ShoppingCartRepository;
import com.app.onlinebookstore.service.CartItemService;
import com.app.onlinebookstore.service.ShoppingCartService;
import com.app.onlinebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;

    @Override
    public CartItemDto save(CreateCartItemRequestDto requestDto) {
        return cartItemService.save(requestDto);
    }

    @Override
    @Transactional
    public ShoppingCartDto getShoppingCart(Pageable pageable) {
        User authenticatedUser = userService.getAuthenticatedUser();
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserId(authenticatedUser.getId()).orElseThrow(
                        () -> new EntityNotFoundException("Can't find shopping cart by user id "
                        + authenticatedUser.getId()));
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUserId(authenticatedUser.getId());
        shoppingCartDto.setCartItems(cartItemService
                .findCartItemsByShoppingCarts(shoppingCart.getId()));
        return shoppingCartDto;
    }
}
