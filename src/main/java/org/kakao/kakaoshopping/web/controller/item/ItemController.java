package org.kakao.kakaoshopping.web.controller.item;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.web.common.paging.request.ItemSearchCondition;
import org.kakao.kakaoshopping.web.dto.item.request.CreateItem;
import org.kakao.kakaoshopping.web.dto.item.request.ReadItem;
import org.kakao.kakaoshopping.web.dto.item.response.ItemComplexView;
import org.kakao.kakaoshopping.web.dto.item.response.ItemSimpleView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@GetMapping("/items")
	public String viewItems(Model model,
		ItemSearchCondition itemSearchCondition,
		Pageable pageable) {

		Page<Item> pageItem = itemService.getSimpleItemList(
			itemSearchCondition.getPageable(pageable.getPageNumber(),
				pageable.getPageSize())
		);
		List<ItemSimpleView> items = pageItem.getContent()
			.stream()
			.map(ItemSimpleView::new).toList();
		model.addAttribute("items", items);
		return "item/itemViews";
	}

	/**
	 * 추후 page 추가
	 **/
	@GetMapping("/item")
	public String viewItem(Model model, ReadItem readItem) {
		Item item = itemService.getItemComplex(readItem.getItemId());
		ItemComplexView itemComplexView = new ItemComplexView(item);
		model.addAttribute("item", itemComplexView);
		return "item/itemView";
	}

	@PostMapping("/seller/createItem")
	public String createItem(@RequestBody CreateItem createItemDTO) {
		Long id = itemService.createItem(createItemDTO.toEntity());
		return "redirect:/page";
	}

}
