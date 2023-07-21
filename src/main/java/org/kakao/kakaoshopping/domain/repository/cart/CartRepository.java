package org.kakao.kakaoshopping.domain.repository.cart;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
