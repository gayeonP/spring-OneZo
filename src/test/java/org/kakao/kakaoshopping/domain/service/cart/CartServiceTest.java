package org.kakao.kakaoshopping.domain.service.cart;

import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.kakao.kakaoshopping.web.dto.cart.request.EditCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CartServiceTest {

	@Autowired
	CartService cartService;

	@Test
	void addItemCartTest() {
		CreateCart dto = new CreateCart(10);
		cartService.addCart(dto.toEntity(), 1L, 1L);
	}

	@Test
	void addItemsCartTest() {
		CreateCart dto = new CreateCart(10);
		for(long i=1 ; i<=5 ; i++){
			cartService.addCart(dto.toEntity(), i, 1L);
		}
	}

	@Test
	@Transactional
	void getItemsInCartTest() {
		List<Cart> carts = cartService.getItemsInCart(1L);
		assertThat(carts.size()).isEqualTo(0);
	}

	@Test
	void updateCart() {
		EditCart editCart = new EditCart(30, 1L);
		cartService.updateCart(editCart.toEntity());
	}

	@Test
	void updateOrderStatus() {
		cartService.updateOrderStateCart(1L);
	}

	@Test
	void deleteItemInCart() {
		cartService.deleteItemInCart(new EditCart(null, 1L).toEntity());
	}

	@Test
	void deleteCart() {
		cartService.deleteCart(1L);
	}
}