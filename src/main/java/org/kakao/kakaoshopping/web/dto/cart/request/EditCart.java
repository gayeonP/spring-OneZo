package org.kakao.kakaoshopping.web.dto.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCart {
    private Integer quantity;

    public Cart toEntity() {
        return Cart.toEdit()
                .quantity(quantity)
                .build();
    }
}
