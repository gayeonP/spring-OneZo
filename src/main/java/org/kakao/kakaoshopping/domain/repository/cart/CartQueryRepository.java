package org.kakao.kakaoshopping.domain.repository.cart;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.user.User;

import java.util.List;

public interface CartQueryRepository {
    public List<Cart> findAllByUser(User user);

    public void deleteByUser(User user);
}
