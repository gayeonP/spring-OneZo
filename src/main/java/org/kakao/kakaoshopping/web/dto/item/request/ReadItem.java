package org.kakao.kakaoshopping.web.dto.item.request;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.Builder;
import lombok.Data;
import org.kakao.kakaoshopping.domain.entity.item.Item;

/**
 * 기능 : 특정 상품을 담기 위한 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
@Data
public class ReadItem {

	private Long itemId;

	@Builder
	public ReadItem(Long itemId) {
		this.itemId = itemId;
	}

	public Item toEntity() {
		return Item.byId()
			.id(itemId)
			.buildById();
	}
}
