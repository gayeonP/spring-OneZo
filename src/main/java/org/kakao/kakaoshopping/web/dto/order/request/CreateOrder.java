package org.kakao.kakaoshopping.web.dto.order.request;

import java.util.List;
import java.util.stream.Collectors;

import org.kakao.kakaoshopping.domain.entity.embedded.Address;
import org.kakao.kakaoshopping.domain.entity.embedded.Delivery;
import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.entity.order.OrderItem;
import org.kakao.kakaoshopping.domain.enums.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 회원이 프론트에서 form에 작성한 데이터를 가져와서 엔티티로 바꿈
public class CreateOrder {

	private String zipCode;
	private String city;
	private String street;
	private String headNumber;
	private String middleNumber;
	private String tailNumber;
	private String deliveryRequest;
	private Payment payment;
	private List<CreateOrderItem> orderItems;

	public Order toEntity() {
		List<OrderItem> orderItems1 = parseOrderItems();

		return Order.builder()
			.delivery(parseDelivery())
			.payment(payment)
			.orderItems(parseOrderItems())
			.build();
	}

	private Delivery parseDelivery() {
		return Delivery.builder()
			.address(parseAddress())
			.phoneNumber(parsePhoneNumber())
			.build();
	}

	private Address parseAddress() {
		return Address.builder()
			.zipCode(zipCode)
			.city(city)
			.street(street)
			.build();
	}

	private PhoneNumber parsePhoneNumber() {
		return PhoneNumber.builder()
			.headNumber(headNumber)
			.middleNumber(middleNumber)
			.tailNumber(tailNumber)
			.build();
	}

	private List<OrderItem> parseOrderItems() {
		return orderItems.stream()
			.map(CreateOrderItem::toEntity)
			.collect(Collectors.toList());
	}
}
