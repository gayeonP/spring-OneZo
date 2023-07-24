package org.kakao.kakaoshopping.web.dto.order.response;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;

import lombok.Data;

@Data
/**
 * 기능 : 주문 상세 데이터를 생성할 DTO
 * 작성자 - 장원준
 * 작성일 - 2023.07.20
 * 수정자 - 임창준
 * 수정일 - 2023.07.21
 */
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
