package org.kakao.kakaoshopping.domain.repository.item;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
