package org.kakao.kakaoshopping.domain.service.item;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.item.ItemRepository;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.item.response.ItemComplexView;
import org.kakao.kakaoshopping.web.dto.item.response.ItemSimpleView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<ItemSimpleView> getSimpleItemList(Pageable pageable) {
        Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("id").descending());

        return itemRepository.findAll(pageable)
                .stream()
                .map(item -> ItemSimpleView.builder()
                        .name(item.getName())
                        .price(item.getPrice())
                        .imagePath(item.getImagePath())
                        .build()).toList();
    }

    public ItemComplexView getItemComplex(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("없음"));
        return ItemComplexView.builder()
                .imagePath(item.getImagePath())
                .itemDetail(item.getItemDetail())
                .name(item.getName())
                .sellerName(item.getSeller().getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .build();
    }


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
