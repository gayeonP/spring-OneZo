package org.kakao.kakaoshopping.web.dto.order.request;

import java.util.List;
import java.util.stream.Collectors;

import org.kakao.kakaoshopping.domain.entity.Order;
import org.kakao.kakaoshopping.domain.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {

	private Long memberId;

	private List<CreateOrderItem> orderItems;

	public Order toEntity() {
		List<OrderItem> items = orderItems.stream()
			.map(CreateOrderItem::toEntity)
			.collect(Collectors.toList());

		return Order.builder()
			.memberId(memberId)
			.orderItems(items)
			.build();
	}
}
