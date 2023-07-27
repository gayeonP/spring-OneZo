package org.kakao.kakaoshopping.web.dto.item.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 기능 : 모든 상품을 리스트를 보여주기 위해 상품을 담는 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSimpleView {
    private Long id;
    private String name;
    @NumberFormat(pattern = "#,##0")
    private BigDecimal price;
    private String imagePath;

    public ItemSimpleView(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.imagePath = item.getImagePath();
        this.id = item.getId();
    }
}
