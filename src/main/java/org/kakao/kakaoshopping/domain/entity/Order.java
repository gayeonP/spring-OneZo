package org.kakao.kakaoshopping.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// todo 추후에 Member 엔티티와 연관관계 맺도록 수정해야 함
	private Long memberId;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@Builder
	public Order(Long memberId, List<OrderItem> orderItems) {
		this.memberId = memberId;
		this.orderItems = orderItems;
	}

	@Builder(builderMethodName = "toEdit")
	// todo 나중에 필요 시 파라미터 추가
	public Order(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addOrderItem(OrderItem orderItem) {
		if (orderItems.size() > 0) {
			orderItems.remove(orderItem);
		}
		orderItem.setOrder(this);
		orderItems.add(orderItem);
		System.out.println("orderItem = " + orderItem);
	}

	public void edit(Order order) {
		this.orderItems = order.getOrderItems();
	}
}

