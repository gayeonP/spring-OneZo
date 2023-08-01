package org.kakao.kakaoshopping.web.dto.order.request;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 상품 정보에 대한 데이터가 들어온다.
public class CreateOrderItem {

	private Long itemId;
	private Integer quantity;

	public OrderItem toEntity() {
		return OrderItem.builder()
			.quantity(quantity)
			.build();
	}
}
