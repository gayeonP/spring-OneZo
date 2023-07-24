package org.kakao.kakaoshopping.web.dto.order.request;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;
import org.kakao.kakaoshopping.web.dto.item.request.ReadItem;

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
	private BigDecimal price;

	public OrderItem toEntity() {
		ReadItem readItem = ReadItem.builder()
			.itemId(itemId)
			.build();

		return OrderItem.builder()
			.quantity(quantity)
			.price(price)
			.item(readItem.toEntity())
			.build();
	}
}
