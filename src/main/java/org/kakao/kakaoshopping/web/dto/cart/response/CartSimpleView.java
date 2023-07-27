package org.kakao.kakaoshopping.web.dto.cart.response;
import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartSimpleView {
	private Long cartId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private Long itemId;
	private final String imagePath = "https://cdn.011st.com/11dims/resize/300/11src/product/5931662112/L300.jpg?708000000";
	public CartSimpleView(Cart cart){
		this.cartId = cart.getId();
		this.name = cart.getItem().getName();
		this.quantity = cart.getQuantity();
		this.price = cart.getItem().getPrice();
		this.itemId = cart.getItem().getId();
	}
}


