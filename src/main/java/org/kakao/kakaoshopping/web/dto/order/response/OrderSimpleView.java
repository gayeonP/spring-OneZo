package org.kakao.kakaoshopping.web.dto.order.response;

import static org.kakao.kakaoshopping.domain.enums.Payment.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.enums.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기능 : 주문 정보 데이터를 생성할 DTD
 * 작성자 - 장원준
 * 작성일 - 2023.07.20
 * 수정자 - 임창준
 * 수정일 - 2023.07.21
 */
@Data
@NoArgsConstructor
public class OrderSimpleView {

	// 주문 아이디
	private Long id;

	// 주문자 아이디
	private Long memberId;

	// 주문 아이템들
	private List<OrderItemSimpleView> orderItems = new ArrayList<>();

	// 총 상품가격
	private BigDecimal totalPrice;

	// 결제 수단
	private Payment payment = EMPTY;

	// 배송 주소
	private String zipCode;
	private String city;
	private String street;

	// 전화번호
	private String phoneNumber;

	public OrderSimpleView(Order order) {
		this.id = order.getId();
		this.memberId = order.getUser().getId();
		this.zipCode = order.getDelivery().getAddress().getZipCode();
		this.city = order.getDelivery().getAddress().getCity();
		this.street = order.getDelivery().getAddress().getStreet();
		this.phoneNumber = order.getDelivery().getPhoneNumber().toStringPhone();
		this.orderItems = order.getOrderItems().stream()
			.map(OrderItemSimpleView::new)
			.toList();
	}
}
