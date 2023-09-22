package com.app.onlinebookstore.controller;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.ShoppingCartDto;
import com.app.onlinebookstore.dto.UpdateCartItemDto;
import com.app.onlinebookstore.service.CartItemService;
import com.app.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @Operation(summary = "Create new shopping cart",
            description = "Return created shopping cart")
    @PostMapping
    public CartItemDto createShoppingCart(@RequestBody @Valid CreateCartItemRequestDto requestDto) {
        return shoppingCartService.save(requestDto);
    }

    @Operation(summary = "Get shopping cart",
            description = "Get shopping cart")
    @GetMapping
    public ShoppingCartDto getShoppingCart(Authentication authentication, Pageable pageable) {
        return shoppingCartService.getShoppingCart(authentication, pageable);
    }

    @Operation(summary = "Update info about existing cart-item",
            description = " Get updated cart-item")
    @PutMapping("/cart-item/{id}")
    public CartItemDto update(@RequestBody @Valid UpdateCartItemDto updateCartItemDto,
                              @PathVariable Long id) {
        return cartItemService.update(id, updateCartItemDto);
    }

    @Operation(summary = "Delete the cart-item by id",
            description = " Delete the cart-item with id")
    @DeleteMapping("/cart-items/{id}")
    public void delete(@PathVariable Long id) {
        cartItemService.delete(id);
    }
}
