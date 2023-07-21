package org.kakao.kakaoshopping.domain.entity.item;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.user.User;

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
@Table(name = "ITEM_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(50) DEFAULT ''", nullable = false)
	private String name;

	@Column(columnDefinition = "DECIMAL(18, 2) DEFAULT 0", nullable = false)
	private BigDecimal price;

	@Column(columnDefinition = "INT DEFAULT 0", nullable = false)
	private Integer stock;

	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
	private String imagePath;

	@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
	private String itemDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User seller;

	@Builder
	public Item(String name, BigDecimal price, Integer stock, String imagePath, String itemDetail, User seller) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.imagePath = imagePath;
		this.itemDetail = itemDetail;
		this.seller = seller;
	}

	@Builder(builderMethodName = "toEdit")
	public Item(String name, BigDecimal price, Integer stock, String imagePath, String itemDetail) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.imagePath = imagePath;
		this.itemDetail = itemDetail;
	}
}
