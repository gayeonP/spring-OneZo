package org.kakao.kakaoshopping.domain.entity.cart;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CART_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})// 엔티티가 변경되는 것을 감지하는 리스너
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private Item item;

	@Column(nullable = false)
	@Setter
	private Integer quantity;

	@Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'", nullable = false)
	@Enumerated(EnumType.STRING)
	@Setter
	private OrderStatus orderStatus;

	@CustomCreateDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime regDate;

	public void editCart(Cart cart) {
		this.quantity = cart.getQuantity();
	}

	@Builder
	public Cart(User user, Item item, Integer quantity, OrderStatus orderStatus) {
		this.user = user;
		this.item = item;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
	}

	@Builder(builderMethodName = "toEdit", buildMethodName = "editBuild")
	public Cart(Integer quantity, OrderStatus orderStatus, Long id) {
		this.quantity = quantity;
		this.orderStatus = orderStatus;
		this.id = id;
	}
}