package org.kakao.kakaoshopping.web.dto.order.request;

import org.kakao.kakaoshopping.domain.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItem {

	private Long itemId;
	private Integer quantity;

	public OrderItem toEntity() {
		return OrderItem.builder()
			.itemId(itemId)
			.quantity(quantity)
			.build();
	}
}
