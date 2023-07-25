package org.kakao.kakaoshopping.web.controller.item;

import lombok.RequiredArgsConstructor;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 기능 : 모든 상품 리스트로 조회한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.24
     *
     * @param model
     * @param itemSearchCondition
     * @param pageable
     * @return String
     */
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
     * 기능 : 상품 상세 페이지를 조회한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.24
     *
     * @param model
     * @param readItem
     * @return String
     */
    @GetMapping("/item")
    public String viewItem(Model model, ReadItem readItem) {
        Item item = itemService.getItemComplex(readItem.getItemId());
        ItemComplexView itemComplexView = new ItemComplexView(item);
        model.addAttribute("item", itemComplexView);
        return "item/itemView";
    }

    /**
     * 기능 : 새로운 상품을 등록한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.24
     *
     * @param createItemDTO
     * @return String
     */
    @PostMapping("/seller/createItem")
    public String createItem(@RequestBody CreateItem createItemDTO) {
        Long id = itemService.createItem(createItemDTO.toEntity());
        return "redirect:/page";
    }

}
