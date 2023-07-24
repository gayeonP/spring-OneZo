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
public class ItemComplexView {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String imagePath;
    private String itemDetail;
    private String sellerName;

    public ItemComplexView(Item item){
        this.name = item.getName();
        this.imagePath = item.getImagePath();
        this.itemDetail = item.getItemDetail();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.sellerName = item.getSeller().getName();
    }
}
