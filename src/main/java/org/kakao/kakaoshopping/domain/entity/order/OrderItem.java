package org.kakao.kakaoshopping.domain.entity.order;

import java.time.LocalDateTime;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(value = {AuditingEntityListener.class})
/**
 * 기능 : 주문 상품에 대한 엔티티
 * 작성자 - 장원준
 * 작성일 - 2023.07.20
 * 수정자 - 임창준
 * 수정일 - 2023.07.21
 */
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
