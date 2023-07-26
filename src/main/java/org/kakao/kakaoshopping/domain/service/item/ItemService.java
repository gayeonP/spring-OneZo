package org.kakao.kakaoshopping.domain.service.item;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.repository.item.ItemRepository;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.exception.ItemNotFound;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final MessageSource messageSource;


    /**
     * 기능: 상품 목록을 보여주는 기능을 한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     *
     * @param pageable
     * @return
     */
    public Page<Item> getSimpleItemList(Pageable pageable) {
        Pageable pageableDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("id").descending());
        return itemRepository.findAll(pageableDesc);
    }

    /**
     * 기능: 특정 상품에 대한 상세 정보를 전달하는 기능을 한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     *
     * @param itemId
     * @return
     */
    public Item getItemComplex(Long itemId) {
        return findById(itemId);
    }

    /**
     * 기능: 상품 등록을 한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     *
     * @param item
     * @return
     */
    public Long createItem(Item item) {
        return itemRepository.save(item).getId();
    }

    /**
     * 기능: 상품 정보를 수정하는 기능을 한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     * 수정자: 장규민
     * 수정일: 2023.07.25
     *
     * @param editItem
     * @param itemId
     */
    @Transactional
    public Item editItem(Item editItem, Long itemId) {
        // TODO login 기능 완성 후 추가 수정 있을 수 있음
        Item item = findById(itemId);
        item.update(editItem);
        return item;
    }

    /**
     * 기능: 상품을 삭제한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     *
     * @param itemId
     */
    @Transactional
    public void deleteItem(Long itemId) {
        Item item = findById(itemId);
        itemRepository.delete(item);
    }

    /**
     * 기능: 특정 상품을 찾는 기능을 한다.
     * 작성자: 장규민
     * 작성일: 2023.07.24
     *
     * @param id
     * @return
     */
    private Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFound(messageSource.getMessage("error.noItem", null, Locale.getDefault())));
    }
}
