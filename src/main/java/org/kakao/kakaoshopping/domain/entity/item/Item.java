package org.kakao.kakaoshopping.domain.entity.item;

import jakarta.persistence.*;
import lombok.*;
import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.user.User;

import java.math.BigDecimal;

@Entity
@Table(name = "ITEM_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
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
