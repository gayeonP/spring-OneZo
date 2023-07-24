package org.kakao.kakaoshopping.domain.service.item;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.item.ItemRepository;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;

	// public List<ItemSimpleView> getSimpleItemList(int pageNum) {
	//
	// }

	public Long createItem(Item item) {
		return itemRepository.save(item).getId();
	}

	@Transactional
	public void editItem(Item item, Long memberId, Long itemId) {
		User user = userRepository.findById(memberId).get();
		Item findItem = itemRepository.findById(itemId).get();
		findItem.toEdit()
			.name(item.getName())
			.itemDetail(item.getItemDetail())
			.price(item.getPrice())
			.stock(item.getStock())
			.imagePath(item.getImagePath())
			.build();
	}

	public void deleteItem(Long itemId) {
		itemRepository.deleteById(itemId);
	}
}
