package org.kakao.kakaoshopping.web.dto.order.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.order.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기능 : 주문 정보 데이터를 생성할 DTD
 * 작성자 - 장원준
 * 작성일 - 2023.07.20
 * 수정자 - 임창준
 * 수정일 - 2023.07.25
 */
@Data
@NoArgsConstructor
public class OrderSimpleView {

	private Long id;
	private LocalDateTime orderDate;
	private List<OrderItemSimpleView> orderItems = new ArrayList<>();

	public OrderSimpleView(Order order) {
		this.id = order.getId();
		this.orderDate = order.getOrderDate();
		this.orderItems = order.getOrderItems().stream()
			.map(OrderItemSimpleView::new)
			.toList();
	}
}
