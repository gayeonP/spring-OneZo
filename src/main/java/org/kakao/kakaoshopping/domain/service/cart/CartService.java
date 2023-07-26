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

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final MessageSource messageSource;

    // 장바구니 초기 추가
    public void addCart(Cart cart, Long itemId, Long userId) {
        User user = userService.findUser(userId);
        Item item = itemService.getItemComplex(itemId);
        cart.setItem(item);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    // 장바구니 수량 업데이트
    public void updateCart(Cart cart) {
        Cart findCart = findById(cart.getId());
        findCart.setQuantity(cart.getQuantity());
    }

    public List<Cart> getItemsInCart(Long userId){
        User user = userService.findUser(userId);
        return cartRepository.findAllByUser(user);
    }

    // 결제 시 장바구니 결제 여부 업데이트
    public void updateOrderStateCart(Long userId) {
        User user = userService.findUser(userId);
        cartRepository.findAllByUser(user)
                .forEach(cart -> cart.setOrderStatus(OrderStatus.Y));
    }

    // 장바구니 삭제 (in DB)
    public void deleteItemInCart(Cart deleteCart) {
        Cart cart = findById(deleteCart.getId());
        cartRepository.delete(cart);
    }

    public void deleteCart(Long userId) {
        User user = userService.findUser(userId);
        cartRepository.deleteByUser(user);
    }

    private Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new CartNotFound(messageSource.getMessage("error.noCart", null, Locale.getDefault())));
    }
}