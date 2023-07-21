package org.kakao.kakaoshopping.web.dto.order.response;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;

import lombok.Data;

@Data
public class OrderItemSimpleView {

	private Long id;
	private Long itemId;
	private String itemName;
	private Integer quantity;
	private BigDecimal price;

	public OrderItemSimpleView(OrderItem orderItem) {
		this.id = orderItem.getId();
		this.itemId = orderItem.getItem().getId();
		this.itemName = orderItem.getItem().getName();
		this.price = orderItem.getPrice();
		this.quantity = orderItem.getQuantity();
	}
}
