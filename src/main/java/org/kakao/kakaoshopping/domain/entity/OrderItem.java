package org.kakao.kakaoshopping.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class OrderItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// todo 추후에 Item 엔티티와 연관관계 맺도록 수정해야 함
	private Long itemId;

	private Integer quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@Setter
	private Order order;

	@Builder
	public OrderItem(Long itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	@Builder(builderMethodName = "toEdit")
	public OrderItem(Long id, Long itemId, Integer quantity) {
		this.id = id;
		this.itemId = itemId;
		this.quantity = quantity;
	}
}
