package org.kakao.kakaoshopping.web.dto.item.request;

import org.kakao.kakaoshopping.domain.entity.item.Item;

import lombok.Data;

@Data
public class ReadItem {

	private Long itemId;

	public Item toEntity() {
		return Item.byId()
			.id(itemId)
			.build();
	}
}
