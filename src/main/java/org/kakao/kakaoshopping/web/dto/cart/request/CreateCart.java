package org.kakao.kakaoshopping.web.dto.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCart {
    private Integer quantity;

    public Cart toEntity() {
        return Cart.builder()
                .orderStatus(OrderStatus.N)
                .quantity(quantity)
                .build();
    }
}
