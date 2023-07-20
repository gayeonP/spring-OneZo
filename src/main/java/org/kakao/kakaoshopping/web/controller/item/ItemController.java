package org.kakao.kakaoshopping.web.controller.item;

import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.web.annotaion.LoginMember;
import org.kakao.kakaoshopping.web.dto.item.request.CreateItemDTO;
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PostMapping("/seller/createItem")
	public String createItem(@RequestBody CreateItemDTO createItemDTO) {
		Long id = itemService.createItem(createItemDTO.toEntity());

		return "redirect:/page";
	}
}
