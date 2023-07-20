package org.kakao.kakaoshopping.domain.entity.item;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ITEM_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(50) DEFAULT ''", nullable = false)
	private String name;

	@Column(columnDefinition = "INT DEFAULT 0", nullable = false)
	private Integer price;

	@Column(columnDefinition = "INT DEFAULT 0", nullable = false)
	private Integer stock;

	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
	private String imagePath;

	@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
	private String itemDetail;

	@Builder
	public Item(String name, Integer price, Integer stock, String imagePath, String itemDetail) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.imagePath = imagePath;
		this.itemDetail = itemDetail;
	}
}
