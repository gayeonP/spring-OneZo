package org.kakao.kakaoshopping.web.dto.cart.response;
import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartSimpleView {
	private Long id;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	public CartSimpleView(Cart cart){
		this.id = cart.getId();
		this.name = cart.getItem().getName();
		this.quantity = cart.getQuantity();
		this.price = cart.getItem().getPrice();
	}
}


