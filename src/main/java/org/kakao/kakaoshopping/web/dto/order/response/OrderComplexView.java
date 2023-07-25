package org.kakao.kakaoshopping.web.dto.order.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.enums.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기능 : 주문 정보 데이터를 생성할 DTD
 * 작성자 - 장원준
 * 작성일 - 2023.07.25
 * 수정자 - 장원준
 * 수정일 - 2023.07.25
 */
@Data
@NoArgsConstructor
public class OrderComplexView {

	private Long id;
	private Long userId;
	private BigDecimal totalPrice;
	private String zipCode;
	private String city;
	private String street;
	private String phoneNumber;
	private LocalDateTime orderDate;
	private Payment payment;
	private List<OrderItemSimpleView> orderItems = new ArrayList<>();

	public OrderComplexView(Order order) {
		this.id = order.getId();
		this.userId = order.getUser().getId();
		this.zipCode = order.getDelivery().getAddress().getZipCode();
		this.city = order.getDelivery().getAddress().getCity();
		this.street = order.getDelivery().getAddress().getStreet();
		this.phoneNumber = order.getDelivery().getPhoneNumber().toStringPhone();
		this.orderDate = order.getOrderDate();
		this.orderItems = order.getOrderItems().stream()
			.map(OrderItemSimpleView::new)
			.toList();
	}
}
