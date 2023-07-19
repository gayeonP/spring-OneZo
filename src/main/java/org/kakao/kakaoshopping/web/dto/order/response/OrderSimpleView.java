package org.kakao.kakaoshopping.web.dto.order.response;

import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderSimpleView {

	private Long id;

	private Long memberId;

	private List<OrderItemSimpleView> orderItems = new ArrayList<>();

	public OrderSimpleView(Order order) {
		this.id = order.getId();
		this.memberId = order.getMemberId();
		this.orderItems = order.getOrderItems().stream()
			.map(OrderItemSimpleView::new)
			.toList();
	}
}
