package org.kakao.kakaoshopping.domain.entity.order;

import java.time.LocalDateTime;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.item.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "INT DEFAULT 0", nullable = false)
	private Integer quantity;

	@Column(columnDefinition = "INT DEFAULT 0", nullable = false)
	private Integer price;

	@CustomCreateDate
	@Column(updatable = false)
	private LocalDateTime regDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private Item item;

	@Builder
	public OrderItem(int quantity) {
		this.quantity = quantity;
	}

	@Builder(builderMethodName = "toEdit")
	public OrderItem(Long id, Integer quantity) {
		this.id = id;
		this.quantity = quantity;
	}
}
