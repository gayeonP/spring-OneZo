package org.kakao.kakaoshopping.web.dto.item.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;

import java.math.BigDecimal;

/**
 * 기능 : 특정 상품을 자세히 보여주기 위한 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemComplexView {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String imagePath;
    private String itemDetail;
    private String sellerName;
    private Long sellerId;

    public ItemComplexView(Item item) {
        this.name = item.getName();
        this.imagePath = item.getImagePath();
        this.itemDetail = item.getItemDetail();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.sellerName = item.getSeller().getName();
        this.sellerId = item.getSeller().getId();
        this.id = item.getId();
    }
}
