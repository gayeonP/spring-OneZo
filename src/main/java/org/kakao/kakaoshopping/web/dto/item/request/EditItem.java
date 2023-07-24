package org.kakao.kakaoshopping.web.dto.item.request;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditItem {

	@NotEmpty
	private String name;

	private BigDecimal price;

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