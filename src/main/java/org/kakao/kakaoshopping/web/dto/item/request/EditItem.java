package org.kakao.kakaoshopping.web.dto.item.request;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기능 : 수정한 상품을 담기 위한 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
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
			.imagePath(
				"https://newsimg.sedaily.com/2021/04/08/22L06CUBC8_2.png")
			.itemDetail(itemDetail)
			.build();

	}
}