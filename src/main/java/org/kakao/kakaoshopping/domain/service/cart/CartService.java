package org.kakao.kakaoshopping.domain.service.cart;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.repository.cart.CartRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final CartRepository cartRepository;

	// 장바구니 초기 추가
	public Cart addCart(Cart cart) {
		return null;
	}

	// 장바구니 수량, 옵션, 결제 여부 업데이트
	public int updateCart(Cart cart) {
		return 0;
	}

	// 장바구니 삭제
	public void deleteCart(Cart cart) {
	}
}
