package org.kakao.kakaoshopping.web.dto.item.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.kakao.kakaoshopping.domain.entity.item.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSimpleView {
    private String name;
    private BigDecimal price;
    private String imagePath;

    public ItemSimpleView(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.imagePath = item.getImagePath();
    }
}
