package org.kakao.kakaoshopping.web.dto.item.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.item.Item;

import java.math.BigDecimal;

/**
 * 기능 : 수정한 상품을 담기 위한 DTO
 * 작성자 - 박가연
 * 작성일 - 2023.07.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditItem {

    @NotEmpty
    private String name;

    private BigDecimal price;

    private Integer stock;

    private String itemDetail;

    public Item toEntity() {
        return Item.toEdit()
                .name(name)
                .price(price)
                .stock(stock)
                .imagePath("https://cdn.011st.com/11dims/resize/300/11src/product/5931662112/L300.jpg?708000000")
                .itemDetail(itemDetail)
                .build();
    }
}