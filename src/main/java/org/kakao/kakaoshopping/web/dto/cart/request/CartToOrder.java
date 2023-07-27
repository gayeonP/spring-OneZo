package org.kakao.kakaoshopping.web.dto.cart.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartToOrder {

	private Long cartId;
	private Long itemId;
	private int quantity;
}
