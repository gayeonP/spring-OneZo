package org.kakao.kakaoshopping.web.dto.item.request;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.Builder;
import lombok.Data;

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
