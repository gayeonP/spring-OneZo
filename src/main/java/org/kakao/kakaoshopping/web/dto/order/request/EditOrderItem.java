package org.kakao.kakaoshopping.web.dto.order.request;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;

import lombok.Data;

@Data
public class EditOrderItem {

	private Long id;

	private Long itemId;

	private Integer quantity;

	public OrderItem toEntity() {
		return OrderItem.toEdit()
			.id(id)
			//.itemId(itemId)
			.quantity(quantity)
			.build();
	}
}