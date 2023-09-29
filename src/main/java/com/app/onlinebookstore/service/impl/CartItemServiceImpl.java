package com.app.onlinebookstore.service.impl;

import com.app.onlinebookstore.dto.CartItemDto;
import com.app.onlinebookstore.dto.CreateCartItemRequestDto;
import com.app.onlinebookstore.dto.UpdateCartItemDto;
import com.app.onlinebookstore.exaption.EntityNotFoundException;
import com.app.onlinebookstore.mapper.CartItemMapper;
import com.app.onlinebookstore.model.CartItem;
import com.app.onlinebookstore.model.ShoppingCart;
import com.app.onlinebookstore.repository.BookRepository;
import com.app.onlinebookstore.repository.CartItemRepository;
import com.app.onlinebookstore.repository.ShoppingCartRepository;
import com.app.onlinebookstore.service.CartItemService;
import com.app.onlinebookstore.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final BookRepository bookRepository;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartItemDto save(CreateCartItemRequestDto requestDto) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findById(requestDto.getBookId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id "
                        + requestDto.getBookId())));
        cartItem.setQuantity(requestDto.getQuantity());
        Long userId = userService.getAuthenticatedUser().getId();
        setShoppingCartAndCartItems(shoppingCartRepository
                .findByUserId(userId).get().getId(), cartItem);
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    @Transactional
    public CartItemDto update(Long id, UpdateCartItemDto updateCartItemDto) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find item by id " + id));
        cartItem.setQuantity(updateCartItemDto.getQuantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public List<CartItemDto> findCartItemsByShoppingCarts(Long id) {
        return cartItemRepository.findCartItemsByShoppingCartId(id).stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Long userId = userService.getAuthenticatedUser().getId();
        CartItem cartItem = cartItemRepository
                .findCartItemsByCartIdAndUserId(id, userId);
        cartItemRepository.delete(cartItem);
    }

    private void setShoppingCartAndCartItems(Long shoppingCartId, CartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart by id "
                        + shoppingCartId));
        cartItem.setShoppingCart(shoppingCart);
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);
        if (shoppingCart.getCartItems().isEmpty()) {
            shoppingCart.setCartItems(cartItems);
        } else {
            shoppingCart.getCartItems().add(cartItem);
        }
    }
}
