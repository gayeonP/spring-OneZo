package org.kakao.kakaoshopping.domain.service.item;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.item.ItemRepository;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.item.response.ItemComplexView;
import org.kakao.kakaoshopping.web.dto.item.response.ItemSimpleView;
import org.kakao.kakaoshopping.web.exception.ItemNotFound;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final MessageSource messageSource;

	public Page<Item> getSimpleItemList(Pageable pageable) {
		Pageable pageableDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
			Sort.by("id").descending());
		return itemRepository.findAll(pageableDesc);
	}

	public Item getItemComplex(Long itemId) {
		return findById(itemId);
	}

	public Long createItem(Item item) {
		return itemRepository.save(item).getId();
	}

	@Transactional
	public void editItem(Item item, Long memberId, Long itemId) {
		// TODO login 기능 완성 후 추가 수정 있을 수 있음
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
		Item item = findById(itemId);
		itemRepository.delete(item);
	}

	private Item findById(Long id) {
		return itemRepository.findById(id)
			.orElseThrow(() ->
				new ItemNotFound(messageSource.getMessage("error.noItem", null, Locale.getDefault())));
	}
}
