package org.kakao.kakaoshopping.web.controller.item;

import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.web.dto.item.request.CreateItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PostMapping("/seller/createItem")
	public String createItem(@RequestBody CreateItem createItemDTO) {
		Long id = itemService.createItem(createItemDTO.toEntity());

		return "redirect:/page";
	}

}
