package org.kakao.kakaoshopping.domain.service.cart;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;
import org.kakao.kakaoshopping.domain.repository.cart.CartRepository;
import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.exception.CartNotFound;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final MessageSource messageSource;

    // 장바구니 초기 추가
    @Transactional
    public void addCart(Cart cart, Long itemId, Long userId) {
        User user = userService.findUser(userId);
        Item item = itemService.getItemComplex(itemId);
        cart.setItem(item);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    // 장바구니 수량 업데이트
    @Transactional
    public void updateCart(Long cartId, Cart cart) {
        Cart findCart = findById(cartId);
        findCart.setQuantity(cart.getQuantity());
    }

    // 결제 시 장바구니 결제 여부 업데이트
    @Transactional
    public void updateOrderStateCart(Long userId) {
        User user = userService.findUser(userId);
        cartRepository.findAllByUser(user)
                .forEach(cart -> cart.setOrderStatus(OrderStatus.Y));
    }

    // 장바구니 삭제 (in DB)
    @Transactional
    public void deleteItemInCart(Long cartId) {
        Cart cart = findById(cartId);
        cartRepository.delete(cart);
    }

    @Transactional
    public void deleteCart(Long userId) {
        User user = userService.findUser(userId);
    }

    private Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new CartNotFound(messageSource.getMessage("error.noCart", null, Locale.getDefault())));
    }
}