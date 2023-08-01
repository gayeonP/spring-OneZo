package org.kakao.kakaoshopping.web.dto.item.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;

import java.math.BigDecimal;

/**
 * 기능 : 새로운 상품을 등록하기 위한 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateItem {
    @NotEmpty
    private String name;

    private BigDecimal price;

    private Integer stock;

    private String itemDetail;

    private User seller;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .imagePath("https://newsimg.sedaily.com/2021/04/08/22L06CUBC8_2.png")
                .itemDetail(itemDetail)
                .seller(seller)
                .build();
    }
}