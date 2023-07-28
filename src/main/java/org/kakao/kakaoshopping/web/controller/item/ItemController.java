package org.kakao.kakaoshopping.web.controller.item;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.api.common.result.ListResult;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.common.paging.request.ItemSearchCondition;
import org.kakao.kakaoshopping.web.dto.item.request.CreateItem;
import org.kakao.kakaoshopping.web.dto.item.request.EditItem;
import org.kakao.kakaoshopping.web.dto.item.request.ReadItem;
import org.kakao.kakaoshopping.web.dto.item.response.ItemComplexView;
import org.kakao.kakaoshopping.web.dto.item.response.ItemSimpleView;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
                            @PageableDefault(size = 9) Pageable pageable) {

        Page<Item> pageItem = itemService.getSimpleItemList(
                itemSearchCondition.getPageable(pageable.getPageNumber(),
                        pageable.getPageSize())
        );

        List<ItemSimpleView> items = pageItem.getContent()
                .stream()
                .map(ItemSimpleView::new).toList();

        ListResult<ItemSimpleView> result = ListResult.<ItemSimpleView>builder()
                .list(items)
                .page(pageItem)
                        .build();

        model.addAttribute("items", result);
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
    public String viewItem(Model model, ReadItem readItem, @LoginUser LoggedInUser loggedInUser) {
        Item item = itemService.getItemComplex(readItem.getItemId());
        ItemComplexView itemComplexView = new ItemComplexView(item);
        model.addAttribute("item", itemComplexView);
        model.addAttribute("user", loggedInUser);
        return "item/itemView";
    }

    @GetMapping("/seller/createItem")
    public String createItemView() {
        return "item/createItemView";
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
    public String createItem(CreateItem createItemDTO, @LoginUser LoggedInUser loggedInUser) {
        Long id = itemService.createItem(createItemDTO.toEntity(), loggedInUser.getUserId());
        return "redirect:/item?itemId=" + id;
    }

    @GetMapping("/seller/editItem")
    public String editItemView(Long itemId, Model model) {
        Item item = itemService.getItemComplex(itemId);
        ItemComplexView complexView = ItemComplexView.builder()
                .id(item.getId())
                .imagePath(item.getImagePath())
                .name(item.getName())
                .price(item.getPrice())
                .build();
        model.addAttribute("item", complexView);
        return "item/editItemView";
    }

    @PostMapping("/seller/editItem")
    public String editItem(EditItem editItem, Model model) {
        Item item = itemService.editItem(editItem.toEntity(), editItem.getItemId());
        ItemComplexView complexView = ItemComplexView.builder()
                .id(item.getId())
                .imagePath(item.getImagePath())
                .name(item.getName())
                .price(item.getPrice())
                .build();
        model.addAttribute("item", complexView);
        return "redirect:/item?itemId="+editItem.getItemId();
    }

    @PostMapping("/seller/deleteItem/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/items";
    }
}
