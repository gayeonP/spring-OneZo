package org.kakao.kakaoshopping.domain.service.cart;

import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void addCartTest() {
        CreateCart dto = new CreateCart(10);
        cartService.addCart(dto.toEntity(), 1L, 1L);
    }
}