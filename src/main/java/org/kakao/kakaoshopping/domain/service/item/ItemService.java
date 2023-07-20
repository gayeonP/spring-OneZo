package org.kakao.kakaoshopping.domain.service.item;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.repository.item.ItemRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	public Long createItem(Item item) {
		return itemRepository.save(item).getId();
	}
}
