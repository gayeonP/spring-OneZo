package org.kakao.kakaoshopping.web.dto.order.request;

import java.util.List;
import java.util.stream.Collectors;

import org.kakao.kakaoshopping.domain.entity.Order;
import org.kakao.kakaoshopping.domain.entity.OrderItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditOrder {

	private Long id;

	private List<EditOrderItem> orderItems;

	public Order toEntity() {
		List<OrderItem> items = orderItems.stream()
			.map(EditOrderItem::toEntity)
			.collect(Collectors.toList());

		return Order.toEdit()
			.orderItems(items)
			.build();
	}
}
