package org.kakao.kakaoshopping.web.dto.item.request;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemDTO {
	private String name;

	private Integer price;

	private Integer stock;

	private String itemDetail;

	public Item toEntity() {
		return Item.builder()
			.name(name)
			.price(price)
			.stock(stock)
			.imagePath("path")
			.itemDetail(itemDetail)
			.build();
	}
}