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

    /**
     * 기능: 장바구니를 추가한다.
     * 작성자: 장규민
     * 작성일: 2023.07.25
     *
     * @param cart
     * @param itemId
     * @param userId
     */
    public void addCart(Cart cart, Long itemId, Long userId) {
        User user = userService.findUser(userId);
        Item item = itemService.getItemComplex(itemId);
        cart.setItem(item);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    /**
     * 기능: 장바구니에 있는 아이템의 수량을 업데이트한다.
     * 작성자: 박가연
     * 작성일: 2023.07.25
     *
     * @param cart
     */
    public void updateCart(Cart cart) {
        Cart findCart = findById(cart.getId());
        findCart.setQuantity(cart.getQuantity());
    }

    /**
     * 기능: 사용자가 갖고 있는 장바구니 조회
     * 작성자: 장규민
     * 작성일: 2023.07.25
     *
     * @param userId
     * @return List<Cart>
     */
    public List<Cart> getItemsInCart(Long userId){
        User user = userService.findUser(userId);
        return cartRepository.findAllByUser(user);
    }

    /**
     * 기능: 장바구니 결제 상태를 업데이트한다.
     * 작성자: 박가연
     * 작성일: 2023.07.25
     *
     * @param userId
     */
    public void updateOrderStateCart(Long userId) {
        User user = userService.findUser(userId);
        cartRepository.findAllByUser(user)
                .forEach(cart -> cart.setOrderStatus(OrderStatus.Y));
    }

    /**
     * 기능: 장바구니를 1개 삭제한다.
     * 작성자: 장규민
     * 작성일: 2023.07.25
     *
     * @param deleteCart
     */
    public void deleteItemInCart(Cart deleteCart) {
        Cart cart = findById(deleteCart.getId());
        cartRepository.delete(cart);
    }

    /**
     * 기능: 유저가 들고 있던 장바구니를 삭제한다.
     * 작성자: 장규민
     * 작성일: 2023.07.25
     *
     * @param userId
     */
    public void deleteCart(Long userId) {
        User user = userService.findUser(userId);
        cartRepository.deleteByUser(user);
    }

    /**
     * 기능: 특정 장바구니를 찾는다.
     * 작성자: 박가연
     * 작성일: 2023.07.25
     *
     * @param id
     * @return Cart
     */
    private Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new CartNotFound(messageSource.getMessage("error.noCart", null, Locale.getDefault())));
    }
}