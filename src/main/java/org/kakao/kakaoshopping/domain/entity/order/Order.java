package org.kakao.kakaoshopping.domain.entity.order;

import static org.kakao.kakaoshopping.domain.enums.CancelStatus.*;
import static org.kakao.kakaoshopping.domain.enums.Payment.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.embedded.Delivery;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.CancelStatus;
import org.kakao.kakaoshopping.domain.enums.PayStatus;
import org.kakao.kakaoshopping.domain.enums.Payment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Delivery Delivery;

	@Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'", nullable = false)
	@Enumerated(EnumType.STRING)
	private PayStatus payStatus;

	@Column(columnDefinition = "VARCHAR(1)", nullable = false)
	@Enumerated(EnumType.STRING)
	private CancelStatus cancelStatus = N;

	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	@Enumerated(EnumType.STRING)
	private Payment payment = EMPTY;

	@CustomCreateDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime orderDate;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Setter
	private List<OrderItem> orderItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User user;

	@Builder
	public Order(Long memberId, List<OrderItem> orderItems) {
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
	}

	public void edit(Order order) {
		this.orderItems = order.getOrderItems();
	}
}

